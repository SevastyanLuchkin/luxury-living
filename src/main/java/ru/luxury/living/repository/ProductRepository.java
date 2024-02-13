package ru.luxury.living.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.luxury.living.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findAll(Specification<Product> specification, Pageable pageable);

    @Query("select p from Product p where p.title like %:text%") //todo like with ignore case
    Page<Product> findAllByTitleLike(String text, Pageable pageable);

    @Query("select p from Product p where p.description like %:text%") //todo like with ignore case
    Page<Product> findAllByDescriptionLike(String text, Pageable pageable);
}
