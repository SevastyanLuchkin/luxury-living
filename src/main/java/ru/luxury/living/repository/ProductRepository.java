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
            select p from Product p join p.categories cat where
            (coalesce(:brandIds) is null or p.brand.id in (:brandIds)) and
            (coalesce(:categoryIds) is null or cat.id in (:categoryIds)) and
            (coalesce(:collectionIds) is null or p.collection.id in (:collectionIds)) and
            (:typeId is null or p.type.id = :typeId) and
             p.inStock = :inStock
            """)
    Page<Product> findProducts(
            List<Long> brandIds,
            List<Long> categoryIds,
            List<Long> collectionIds,
            Long typeId,
            Boolean inStock,
            Pageable pageable
    );

    @Query("""
            select p from Product p where lower(p.title) = lower(:name) and lower(p.brand.title) = lower(:brand)
            """)
    List<Product> findByTitleIgnoreCaseAndByBrandTitle(String name, String brand);

    List<Product> findByTitleIgnoreCaseAndAndDescriptionIgnoreCase(String title, String description);
}
