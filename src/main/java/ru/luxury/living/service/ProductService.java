package ru.luxury.living.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.luxury.living.model.Product;
import ru.luxury.living.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

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
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "brand_number"));
        return productRepository.findAll(pageRequest);
    }

    public Page<Product> search(
            List<Long> brandIds,
            List<Long> categoryIds,
            List<Long> collectionIds,
            Long typeId,
            Boolean inStock,
            Pageable pageable
    ) {
        return productRepository.findProducts(brandIds, categoryIds, collectionIds, typeId, inStock, pageable);
    }

    public Page<Product> textSearch(String text, Pageable pageable) {
        Page<Product> products = productRepository.findAllByTitleLike(text, pageable);
        return !products.isEmpty() ? products : productRepository.findAllByDescriptionLike(text, pageable);
    }
}
