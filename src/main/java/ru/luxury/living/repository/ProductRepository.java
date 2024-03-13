package ru.luxury.living.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.luxury.living.model.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAll(Specification<Product> specification, Pageable pageable);

    @Query("select p from Product p where lower(p.title) like lower(concat('%', :text,'%'))")
    Page<Product> findAllByTitleLike(String text, Pageable pageable);

    @Query("select p from Product p where lower(p.description) like lower(concat('%', :text,'%'))")
    Page<Product> findAllByDescriptionLike(String text, Pageable pageable);

    @Query(""" 
            select p from Product p where
            (coalesce(:brandIds) is null or p.brand.id in (:brandIds)) and
            (:categoryId is null or p.category.id = :categoryId) and
            (coalesce(:collectionIds) is null or p.collection.id in (:collectionIds)) and
            (:typeId is null or p.type.id = :typeId) and
             p.inStock = :inStock
            """)
    Page<Product> findProducts(
            List<Long> brandIds,
            Long categoryId,
            List<Long> collectionIds,
            Long typeId,
            Boolean inStock,
            Pageable pageable
    );
}
