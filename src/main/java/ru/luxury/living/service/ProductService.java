package ru.luxury.living.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.luxury.living.model.Product;
import ru.luxury.living.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    public static final List<Boolean> ALL = List.of(true, false);
    public static final List<Boolean> ACTIVE = List.of(true);
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
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "brand.number"));
        return productRepository.findAll(pageRequest);
    }

    public Page<Product> search(
            List<Long> brandIds,
            List<Long> categoryIds,
            List<Long> collectionIds,
            Long typeId,
            Boolean inStock,
            Boolean admin,
            Pageable pageable
    ) {
        PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "brand.number"));
        return productRepository.findProducts(brandIds, categoryIds, collectionIds, typeId, inStock, Boolean.TRUE.equals(admin) ? ALL : ACTIVE, pageRequest);
    }

    public Page<Product> textSearch(String text, Boolean admin, Pageable pageable) {
        Page<Product> products = productRepository.findAllByTitleLike(text, Boolean.TRUE.equals(admin) ? ALL : ACTIVE, pageable);
        return !products.isEmpty() ? products : productRepository.findAllByDescriptionLike(text, pageable);
    }

    public Page<Product> getLiked(Pageable pageable) {
        return productRepository.findLiked(pageable);
    }

    @Transactional
    public void setLiked(Long productId, Boolean liked) {
        productRepository.findById(productId).orElseThrow().setLiked(liked);
    }
}
