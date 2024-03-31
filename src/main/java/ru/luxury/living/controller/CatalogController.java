package ru.luxury.living.controller;

import com.poiji.bind.Poiji;
import com.poiji.option.PoijiOptions;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.luxury.living.model.Brand;
import ru.luxury.living.model.CatalogExcel;
import ru.luxury.living.model.Category;
import ru.luxury.living.model.Product;
import ru.luxury.living.model.Type;
import ru.luxury.living.repository.BrandRepository;
import ru.luxury.living.repository.CategoryRepository;
import ru.luxury.living.repository.CollectionRepository;
import ru.luxury.living.repository.ProductRepository;
import ru.luxury.living.repository.TypeRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping("catalog")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Операции с каталогом")
public class CatalogController {

    private final BrandRepository brandRepository;
    private final CategoryRepository categoryRepository;
    private final TypeRepository typeRepository;
    private final ProductRepository productRepository;
    private final CollectionRepository collectionRepository;

    @PutMapping
    @Transactional
    public void upload(@RequestParam("file") MultipartFile file, @RequestParam int line) throws IOException {
        try (InputStream is = file.getInputStream()) {
            XSSFWorkbook wb = new XSSFWorkbook(is);

            List<CatalogExcel> catalog =
                    Poiji.fromExcel(wb.getSheetAt(0), CatalogExcel.class, PoijiOptions.PoijiOptionsBuilder.settings(line).build())
                            .stream().toList();

            upload(catalog);
        }
    }


    @PostMapping
    @Transactional
    public void parse(@RequestParam("file") MultipartFile file, @RequestParam Long line) {
        try (InputStream is = file.getInputStream()) {
            XSSFWorkbook wb = new XSSFWorkbook(is);

            List<CatalogExcel> catalog =
                    Poiji.fromExcel(wb.getSheetAt(0), CatalogExcel.class)
                            .stream().toList();

            upload(catalog);

        } catch (Exception e) {
            log.error("Ошибка при попытке чтения excel файла", e);
        }
    }

    private void upload(List<CatalogExcel> catalog) {
        Map<String, Brand> brandByName = catalog.stream()
                .map(row -> {
                    Brand brand = brandRepository.findByTitleIgnoreCase(row.getBrand())
                            .orElseGet(() -> new Brand().setTitle(row.getBrand()));

                    Category category = categoryRepository.findByTitleIgnoreCase(row.getCategory())
                            .orElseGet(() -> categoryRepository.save(new Category().setTitle(row.getCategory())));
                    brand.getCategory().add(category);

                    if (StringUtils.hasText(row.getType())) {
                        Type type = typeRepository.findByTitleIgnoreCase(row.getType())
                                .orElseGet(() -> typeRepository.save(new Type().setTitle(row.getType())));
                        category.getTypes().add(type);
                    }
                    return brandRepository.save(brand);
                })
                .distinct()
                .collect(Collectors.toMap(Brand::getTitle, Function.identity(), (x1, x2) -> x2));

        Map<String, Category> categoryByName = catalog
                .stream()
                .map(CatalogExcel::getCategory)
                .distinct()
                .peek(System.out::println)
                .map(categoryName -> categoryRepository.findByTitleIgnoreCase(categoryName)
                        .orElseThrow())
                .collect(Collectors.toMap(Category::getTitle, Function.identity(), (x1, x2) -> x2));

        Map<String, Type> typeByName = catalog
                .stream()
                .filter(row -> StringUtils.hasText(row.getType()))
                .distinct()
                .map(row -> typeRepository.findByTitleIgnoreCase(row.getType())
                        .orElseThrow())
                .collect(Collectors.toMap(Type::getTitle, Function.identity(), (x1, x2) -> x2));

        catalog.stream()
                .map(row -> new Product()
                        .setArticle(row.getArticle())
                        .setTitle(row.getName())
                        .setDescription(row.getDescription())
                        .setType(StringUtils.hasText(row.getType()) ? typeRepository.findByTitleIgnoreCase(row.getType()).orElseThrow() : null)
                        .setCategories(Set.of(categoryRepository.findByTitleIgnoreCase(row.getCategory()).orElseThrow()))
                        .setBrand(brandRepository.findByTitleIgnoreCase(row.getBrand()).orElseThrow())
                        .setMaterials(row.getMaterials())
                        .setCountry(row.getCountry())
                        .setVolume(row.getVolume())
                        .setPrice(getPrice(row))
                        .setCollectionTitle(row.getCollection())
                        .setInStock(Objects.equals("+", row.getInStock()))
                ).forEach(productRepository::save);

        log.info("finished {}", catalog.size());
    }

    @PostMapping("collections")
    @Transactional
    public void addCollections(@RequestParam("file") MultipartFile file) throws IOException {
        try (InputStream is = file.getInputStream()) {
            XSSFWorkbook wb = new XSSFWorkbook(is);

            List<CatalogExcel> catalog =
                    Poiji.fromExcel(wb.getSheetAt(0), CatalogExcel.class)
                            .stream().toList();


            AtomicInteger updated = new AtomicInteger();
            AtomicInteger empty = new AtomicInteger();
            AtomicInteger multiply = new AtomicInteger();
            catalog.forEach(ce -> {
                List<Product> products = productRepository.findByTitleIgnoreCaseAndAndDescriptionIgnoreCase(ce.getName(), ce.getDescription());
                if (products.size() == 1) {
                    Product product = products.get(0).setCollectionTitle(ce.getCollection());
                    productRepository.save(product);
                    updated.getAndIncrement();
                    log.info("product updated {}", product);
                } else if (products.size() == 0) {
                    empty.getAndIncrement();
                    log.info("empty products {} {} {}", ce.getName(), ce.getDescription(), products);
                } else {
                    multiply.getAndIncrement();
                    log.info("multiply products {} {} {}", ce.getName(), ce.getDescription(), products);
                }
            });
            log.info("updated empty multiply {} {} {}", updated.get(), empty.get(), multiply.get());
        }
    }

    private int getPrice(CatalogExcel row) {
        try {
            return Integer.parseInt(row.getPrice());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private Brand getEnrichedBrand(CatalogExcel row) {
        Brand newBrand = new Brand().setTitle(row.getBrand());
        Category category = categoryRepository.findByTitleIgnoreCase(row.getCategory())
                .orElseGet(() -> getEnrichedCategory(row));
        newBrand.getCategory().add(category);
        return brandRepository.save(newBrand);
    }

    private Category getEnrichedCategory(CatalogExcel row) {
        Category newCategory = new Category().setTitle(row.getCategory());
        Type type = typeRepository.findByTitleIgnoreCase(row.getType())
                .orElseGet(() -> typeRepository.save(new Type().setTitle(row.getType())));
        newCategory.getTypes().add(type);
        return newCategory;
    }
}
