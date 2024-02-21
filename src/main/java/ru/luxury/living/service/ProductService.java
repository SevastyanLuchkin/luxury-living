package ru.luxury.living.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.luxury.living.model.Brand;
import ru.luxury.living.model.Category;
import ru.luxury.living.model.Collection;
import ru.luxury.living.model.Product;
import ru.luxury.living.model.Type;
import ru.luxury.living.repository.BrandRepository;
import ru.luxury.living.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final TypeService typeService;
    private final BrandService brandService;
    private final BrandRepository brandRepository;
    private final CollectionService collectionService;

    @PostConstruct
    public void pc() {
        init();
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public Product getById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    public Product update(Long id, Product product) {
        productRepository.findById(id).orElseThrow();
        product.setId(id);
        return productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Page<Product> search(
            List<Long> brandIds,
            Long categoryId,
            List<Long> collectionIds,
            Long typeId,
            Boolean inStock,
            Pageable pageable
    ) {
        return productRepository.findProducts(brandIds, categoryId, collectionIds, typeId, inStock, pageable);
    }

    private void init() {
        Page<Type> types = typeService.findAll(Pageable.unpaged());
        Type glasses = null;
        Type plates = null;
        if (types.isEmpty()) {
            Type tea = new Type();
            tea.setTitle("Чайные наборы");
            tea.setNumber(1);
            typeService.create(tea);

            Type coffee = new Type();
            coffee.setTitle("Кофейные наборы");
            coffee.setNumber(2);
            typeService.create(coffee);

            plates = new Type();
            plates.setTitle("Тарелки");
            plates.setNumber(3);
            typeService.create(plates);

            Type saucers = new Type();
            saucers.setTitle("Блюда");
            saucers.setNumber(4);
            typeService.create(saucers);

            Type trays = new Type();
            trays.setTitle("Подносы");
            trays.setNumber(5);
            typeService.create(trays);

            Type cutlery = new Type();
            cutlery.setTitle("Столовые приборы");
            cutlery.setNumber(6);
            typeService.create(cutlery);

            Type wineglasses = new Type();
            wineglasses.setTitle("Бокалы");
            wineglasses.setNumber(7);
            typeService.create(wineglasses);

            glasses = new Type();
            glasses.setTitle("Стаканы");
            glasses.setNumber(8);
            typeService.create(glasses);

            Type type = new Type();
            type.setTitle("Тестовый тип 1");
            type.setNumber(1);
            typeService.create(type);

            Type type2 = new Type();
            type2.setTitle("Тестовый тип 2");
            type2.setNumber(2);
            typeService.create(type2);

            Type type3 = new Type();
            type3.setTitle("Тестовый тип 3");
            typeService.create(type3);
        }

        Page<Category> categories = categoryService.findAll(Pageable.unpaged());
        Category category3 = null;
        Category category4 = null;
        if (categories.isEmpty()) {
            Category category = new Category();
            category.setTypes(typeService.findAll(Pageable.unpaged()).getContent());
            category.setTitle("Все");
            category.setNumber(1);
            categoryService.create(category);

            Category category2 = new Category();
            category2.setTypes(typeService.findAll(Pageable.unpaged()).getContent());
            category2.setTitle("Подарки");
            category.setNumber(2);
            categoryService.create(category2);

            category3 = new Category();
            category3.setTypes(typeService.findAll(Pageable.unpaged()).getContent());
            category3.setTitle("Сервировка");
            category.setNumber(3);
            categoryService.create(category3);

            category4 = new Category();
            category4.setTypes(typeService.findAll(Pageable.unpaged()).getContent());
            category4.setTitle("Аксессуары");
            category.setNumber(4);
            categoryService.create(category4);

            Category category5 = new Category();
            category5.setTypes(typeService.findAll(Pageable.unpaged()).getContent());
            category5.setTitle("Текстиль");
            category.setNumber(5);
            categoryService.create(category5);

            Category category6 = new Category();
            category6.setTypes(typeService.findAll(Pageable.unpaged()).getContent());
            category6.setTitle("Ковры");
            category.setNumber(6);
            categoryService.create(category6);

            Category category7 = new Category();
            category7.setTypes(typeService.findAll(Pageable.unpaged()).getContent());
            category7.setTitle("Обои");
            category.setNumber(7);
            categoryService.create(category7);

            Category category8 = new Category();
            category8.setTypes(typeService.findAll(Pageable.unpaged()).getContent());
            category8.setTitle("Краски");
            category.setNumber(8);
            categoryService.create(category8);

            Category category9 = new Category();
            category9.setTypes(typeService.findAll(Pageable.unpaged()).getContent());
            category9.setTitle("Мебель");
            category.setNumber(9);
            categoryService.create(category9);
        }

        Page<Collection> collections = collectionService.findAll(Pageable.unpaged());
        if (collections.isEmpty()) {
            Collection collection = new Collection();
            collection.setTitle("Тестовая коллекция 1");
            collectionService.create(collection);
        }

        Page<Brand> brands = brandService.findAll(Pageable.unpaged());
        if (brands.isEmpty()) {
            Brand ancienne = new Brand();
            ancienne.setTitle("Ancienne Manufacture Royale");
            ancienne.setCollections(collectionService.findAll(Pageable.unpaged()).getContent());
            ancienne.setCategory(category3);
            brandService.create(ancienne);

            Brand ancienne2 = new Brand();
            ancienne2.setTitle("Ancienne Manufacture Royale");
            ancienne2.setCollections(collectionService.findAll(Pageable.unpaged()).getContent());
            ancienne2.setCategory(category4);
            brandService.create(ancienne2);

            Brand bernardaud = new Brand();
            bernardaud.setTitle("Bernardaud");
            bernardaud.setCollections(collectionService.findAll(Pageable.unpaged()).getContent());
            bernardaud.setCategory(category3);
            brandService.create(bernardaud);

            Brand bernardaud2 = new Brand();
            bernardaud2.setTitle("Bernardaud");
            bernardaud2.setCollections(collectionService.findAll(Pageable.unpaged()).getContent());
            bernardaud2.setCategory(category4);
            brandService.create(bernardaud2);

            Brand christofle = new Brand();
            christofle.setTitle("Christofle");
            christofle.setCollections(collectionService.findAll(Pageable.unpaged()).getContent());
            christofle.setCategory(category3);
            brandService.create(christofle);

            Brand christofle2 = new Brand();
            christofle2.setTitle("Christofle");
            christofle2.setCollections(collectionService.findAll(Pageable.unpaged()).getContent());
            christofle2.setCategory(category4);
            brandService.create(christofle2);

            Brand hermes = new Brand();
            hermes.setTitle("Hermes");
            hermes.setCollections(collectionService.findAll(Pageable.unpaged()).getContent());
            hermes.setCategory(category3);
            brandService.create(hermes);

            Brand hermes2 = new Brand();
            hermes2.setTitle("Hermes");
            hermes2.setCollections(collectionService.findAll(Pageable.unpaged()).getContent());
            hermes2.setCategory(category4);
            brandService.create(hermes2);

            Brand seignolles = new Brand();
            seignolles.setTitle("J.Seignolles");
            seignolles.setCollections(collectionService.findAll(Pageable.unpaged()).getContent());
            seignolles.setCategory(category3);
            brandService.create(seignolles);

            Brand seignolles2 = new Brand();
            seignolles2.setTitle("J.Seignolles");
            seignolles2.setCollections(collectionService.findAll(Pageable.unpaged()).getContent());
            seignolles2.setCategory(category4);
            brandService.create(seignolles2);
        }

        Page<Product> all = findAll(Pageable.unpaged());
        if (all.isEmpty()) {
            Product product = new Product();
            product.setTitle("Набор из 6 стаканов, коллекция Everyday Baccarat");
            product.setDescription("Ничего лучше в жизни не пробовали, несите скорее нам свои деньги и покупайте наш Набор из 6 стаканов");
            product.setBrand(brandRepository.findByTitle("BACCARAT").orElse(brandService.findAll(Pageable.unpaged()).getContent().get(0)));
//            product.setCollection(collectionService.findAll(Pageable.unpaged()).getContent().get(0));
            product.setCategory(category3);
            product.setType(glasses);
            create(product);

            Product product_hermes = new Product();
            product_hermes.setTitle("Набор из 6 стаканов, коллекция Everyday HERMES");
            product_hermes.setDescription("Ничего лучше в жизни не пробовали, несите скорее нам свои деньги и покупайте наш Набор из 6 стаканов HERMES");
            product_hermes.setBrand(brandRepository.findByTitle("HERMES").orElse(brandService.findAll(Pageable.unpaged()).getContent().get(0)));
//            product.setCollection(collectionService.findAll(Pageable.unpaged()).getContent().get(0));
            product_hermes.setCategory(category4);
            product_hermes.setType(plates);
            create(product_hermes);

            Product product2 = new Product();
            product2.setTitle("Сервировочный набор от BACCARAT");
            product2.setDescription("Ничего лучше в жизни не пробовали, несите скорее нам свои деньги и покупайте наш Сервировочный набор от BACCARAT");
            product2.setBrand(brandRepository.findByTitle("BACCARAT").orElse(brandService.findAll(Pageable.unpaged()).getContent().get(0)));
//            product2.setCollection(collectionService.findAll(Pageable.unpaged()).getContent().get(0));
            product2.setCategory(category3);
            product2.setType(glasses);
            product2.setType(typeService.findAll(Pageable.unpaged()).getContent().get(0));

            Product product2_hermes = new Product();
            product2_hermes.setTitle("Сервировочный набор от HERMES");
            product2_hermes.setDescription("Ничего лучше в жизни не пробовали, несите скорее нам свои деньги и покупайте наш Сервировочный набор от HERMES");
            product2_hermes.setBrand(brandRepository.findByTitle("HERMES").orElse(brandService.findAll(Pageable.unpaged()).getContent().get(0)));
//            product2.setCollection(collectionService.findAll(Pageable.unpaged()).getContent().get(0));
            product2_hermes.setCategory(category3);
            product2_hermes.setType(plates);
            product2_hermes.setType(typeService.findAll(Pageable.unpaged()).getContent().get(0));

            Product product3 = new Product();
            product3.setTitle("Чашки от Baccarat");
            product3.setDescription("Ничего лучше в жизни не пробовали, несите скорее нам свои деньги и покупайте наши Чашки от Baccarat");
            product3.setBrand(brandRepository.findByTitle("BACCARAT").orElse(brandService.findAll(Pageable.unpaged()).getContent().get(0)));
//            product2.setCollection(collectionService.findAll(Pageable.unpaged()).getContent().get(0));
            product3.setCategory(category3);
            product3.setType(glasses);
            product3.setType(typeService.findAll(Pageable.unpaged()).getContent().get(0));

            Product product3_hermes = new Product();
            product3_hermes.setTitle("Чашки от HERMES");
            product3_hermes.setDescription("Ничего лучше в жизни не пробовали, несите скорее нам свои деньги и покупайте наши Чашки от HERMES");
            product3_hermes.setBrand(brandRepository.findByTitle("HERMES").orElse(brandService.findAll(Pageable.unpaged()).getContent().get(0)));
//            product2.setCollection(collectionService.findAll(Pageable.unpaged()).getContent().get(0));
            product3_hermes.setCategory(category3);
            product3_hermes.setType(glasses);
            product3_hermes.setType(typeService.findAll(Pageable.unpaged()).getContent().get(0));
        }
    }

    public Page<Product> textSearch(String text, Pageable pageable) {
        Page<Product> products = productRepository.findAllByTitleLike(text, pageable);
        return !products.isEmpty() ? products : productRepository.findAllByDescriptionLike(text, pageable);
    }
}
