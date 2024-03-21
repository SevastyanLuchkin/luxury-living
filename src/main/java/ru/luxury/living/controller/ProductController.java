package ru.luxury.living.controller;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.luxury.living.model.Product;
import ru.luxury.living.service.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productService.create(product);
    }

    @GetMapping("{id}")
    public Product getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

    @PutMapping("{id}")
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @GetMapping
    public Page<Product> findAll(@ParameterObject @PageableDefault(sort = {"brand_number"}, direction = Sort.Direction.ASC) Pageable pageable) {
        return productService.findAll(pageable);
    }

    @GetMapping("search")
    public Page<Product> search(
            @RequestParam(required = false) List<Long> brandIds,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) List<Long> collectionIds,
            @RequestParam(required = false) Long typeId,
            @RequestParam(required = false, defaultValue = "true") Boolean inStock,
            @ParameterObject @PageableDefault(sort = {"created"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return productService.search(brandIds, categoryId, collectionIds, typeId, inStock, pageable);
    }

    @GetMapping("text-search")
    public Page<Product> textSearch(
            @RequestParam String text,
            @ParameterObject @PageableDefault(sort = {"created"}, direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return productService.textSearch(text, pageable);
    }
}
