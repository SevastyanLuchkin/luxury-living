package ru.luxury.living.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.luxury.living.model.Brand;
import ru.luxury.living.model.Category;
import ru.luxury.living.repository.BrandRepository;
import ru.luxury.living.repository.CategoryRepository;
import ru.luxury.living.repository.TypeRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    public static final Category ALL = new Category()
            .setActive(true)
            .setTitle("Все")
            .setNumber(1);

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
        List<Brand> all1 = brandRepository.findAll();
        List<Brand> brands = all1.stream()
                .sorted(Comparator.comparing(Brand::getNumber, Comparator.nullsLast(Long::compareTo)))
                .toList();
        ALL.setBrands(new LinkedHashSet<>(brands));
        Page<Category> all = categoryRepository.findAll(pageable);
        ALL.setTypes(new LinkedHashSet<>(typeRepository.findAll(Sort.by(Sort.Direction.ASC, "title"))));
        if (!CollectionUtils.isEmpty(all.getContent())) {
            List<Category> categories = new ArrayList<>(all.getContent());
            categories.add(0, ALL);
            return new PageImpl<>(categories, pageable, all.getSize() + 1);
        }
        return all;
    }
}
