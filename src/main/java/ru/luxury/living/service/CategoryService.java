package ru.luxury.living.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.luxury.living.model.Category;
import ru.luxury.living.repository.BrandRepository;
import ru.luxury.living.repository.CategoryRepository;
import ru.luxury.living.repository.TypeRepository;

@Service
@RequiredArgsConstructor
public class CategoryService {

    public static final Category ALL = new Category()
            .setActive(true)
            .setTitle("Все");

    private final BrandRepository brandRepository;
    private final TypeRepository typeRepository;
    private final CategoryRepository categoryRepository;

    public void create(Category category) {
        categoryRepository.save(category);
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    public void update(Long id, Category category) {
        categoryRepository.findById(id).orElseThrow();
        category.setId(id);
        categoryRepository.save(category);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }

    public Page<Category> findAll(Pageable pageable) {
        ALL.setTypes(typeRepository.findAll());
        ALL.setBrands(brandRepository.findAll());
        return categoryRepository.findAll(pageable);
    }
}
