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
import org.springframework.web.bind.annotation.RestController;
import ru.luxury.living.model.Type;
import ru.luxury.living.service.TypeService;

@RestController
@RequiredArgsConstructor
@RequestMapping("types")
public class TypeController {

    private final TypeService typeService;

    @PostMapping
    public Type create(@RequestBody Type type) {
        return typeService.create(type);
    }

    @GetMapping("{id}")
    public Type getById(@PathVariable Long id) {
        return typeService.getById(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        typeService.delete(id);
    }

    @PutMapping("{id}")
    public Type update(@PathVariable Long id, @RequestBody Type type) {
        return typeService.update(id, type);
    }

    @GetMapping
    public Page<Type> findAll(
            @ParameterObject @PageableDefault(sort = {"number,created"}, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        return typeService.findAll(pageable);
    }
}
