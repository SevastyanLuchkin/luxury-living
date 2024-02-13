package ru.luxury.living.service;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.luxury.living.model.Brand;
import ru.luxury.living.model.Category;
import ru.luxury.living.model.Collection;
import ru.luxury.living.model.Product;
import ru.luxury.living.model.Type;
import ru.luxury.living.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final TypeService typeService;
    private final BrandService brandService;
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
            List<Long> categoryIds,
            List<Long> collectionIds,
            List<Long> typeIds,
            Pageable pageable
    ) {


//        Specification<Product> specification = null;
//        if (!CollectionUtils.isEmpty(categoryIds)) {
        Specification<Product> specification = (product, query, cb) -> {
            Join<Product, Category> categoryJoin = product.join("category");
            return cb.in(categoryJoin.get("id")).value(categoryIds);
        };


        specification.and((product, query, cb) -> {
            Join<Product, Type> categoryJoin = product.join("type");
            return cb.in(categoryJoin.get("id")).value(typeIds);
        });

        Specification<Product> specification1 = (product, query, cb) -> {
            Join<Product, Type> categoryJoin = product.join("type");
            return cb.in(categoryJoin.get("id")).value(typeIds);
        };

//        specification.and((product, query, cb) -> {
//            Join<Product, Brand> brandJoin = product.join("brand");
//            return cb.in(brandJoin.get("id")).value(brandIds);
//        });
//
//        specification.and((product, query, cb) -> {
//            Join<Product, Collection> collectionJoin = product.join("collection");
//            return cb.in(collectionJoin.get("id")).value(collectionIds);
//        });

        return productRepository.findAll(specification, pageable);
    }

    private void init() {
        Page<Type> types = typeService.findAll(Pageable.unpaged());
        if (types.isEmpty()) {
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
        if (categories.isEmpty()) {
            Category category = new Category();
            category.setTypes(typeService.findAll(Pageable.unpaged()).getContent());
            category.setTitle("Тестовая категория 1");
            category.setNumber(1);
            categoryService.create(category);

            Category category2 = new Category();
            category2.setTypes(typeService.findAll(Pageable.unpaged()).getContent());
            category2.setTitle("Тестовая категория 2");
            categoryService.create(category2);
        }

        Page<Collection> collections = collectionService.findAll(Pageable.unpaged());
        if (collections.isEmpty()) {
            Collection collection = new Collection();
            collection.setTitle("Тестовая коллекция 1");
            collectionService.create(collection);
        }

        Page<Brand> brands = brandService.findAll(Pageable.unpaged());
        if (brands.isEmpty()) {
            Brand brand = new Brand();
            brand.setTitle("Тестовый бренд 1");
            brand.setCollections(collectionService.findAll(Pageable.unpaged()).getContent());
            brandService.create(brand);
        }

        Page<Product> all = findAll(Pageable.unpaged());
        if (all.isEmpty()) {
            Product product = new Product();
            product.setTitle("Тестовый продукт 1");
            product.setDescription("Ничего лучше в жизни не пробовали, несите скорее нам свои деньги и покупайте наш Тестовый продукт 1");
            product.setBrand(brandService.findAll(Pageable.unpaged()).getContent().get(0));
            product.setCollection(collectionService.findAll(Pageable.unpaged()).getContent().get(0));
            product.setCategory(categoryService.findAll(Pageable.unpaged()).getContent().get(0));
            product.setType(typeService.findAll(Pageable.unpaged()).getContent().get(0));
            create(product);

            Product product2 = new Product();
            product2.setTitle("Тестовый продукт 2");
            product2.setDescription("Ничего лучше в жизни не пробовали, несите скорее нам свои деньги и покупайте наш Тестовый продукт 2");
            product2.setBrand(brandService.findAll(Pageable.unpaged()).getContent().get(0));
            product2.setCollection(collectionService.findAll(Pageable.unpaged()).getContent().get(0));
            product2.setCategory(categoryService.findAll(Pageable.unpaged()).getContent().get(0));
            product2.setType(typeService.findAll(Pageable.unpaged()).getContent().get(0));
        }
    }

    public Page<Product> textSearch(String text, Pageable pageable) {
        Page<Product> products = productRepository.findAllByTitleLike(text, pageable);
        return !products.isEmpty() ? products : productRepository.findAllByDescriptionLike(text, pageable);
    }
}
