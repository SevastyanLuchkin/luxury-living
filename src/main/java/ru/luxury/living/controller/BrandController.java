package ru.luxury.living.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.luxury.living.model.Brand;
import ru.luxury.living.service.BrandService;

@RestController
@RequestMapping("brand")
@RequiredArgsConstructor
@Tag(name = "Операции с брендами")
public class BrandController {

    private final BrandService brandService;

    @PostMapping
    public Brand create(@Validated @RequestBody Brand brand) {
        return brandService.create(brand);
    }

    @PutMapping("{id}")
    public Brand update(@PathVariable Long id, @Validated @RequestBody Brand brand) {
        return brandService.update(id, brand);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        brandService.delete(id);
    }

    @GetMapping("{id}")
    public Brand getById(@PathVariable Long id) {
        return brandService.getById(id);
    }

    @GetMapping
    public Page<Brand> getAll(@ParameterObject @PageableDefault(sort = {"created"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return brandService.getAll(pageable);
    }
}
