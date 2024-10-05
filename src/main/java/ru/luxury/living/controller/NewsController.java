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
import ru.luxury.living.model.News;
import ru.luxury.living.service.NewsService;

@RestController
@RequestMapping("news")
@RequiredArgsConstructor
@Tag(name = "Операции с новостной панелью")
public class NewsController {

    private final NewsService newsService;

    @PostMapping
    public News create(@Validated @RequestBody News news) {
        return newsService.create(news);
    }

    @PutMapping("{id}")
    public News update(@PathVariable Long id, @Validated @RequestBody News news) {
        return newsService.update(id, news);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        newsService.delete(id);
    }

    @GetMapping("{id}")
    public News getById(@PathVariable Long id) {
        return newsService.getById(id);
    }

    @GetMapping
    public Page<News> findAll(@ParameterObject @PageableDefault(sort = {"newsDate"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return newsService.findAll(pageable);
    }
}
