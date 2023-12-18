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
import ru.luxury.living.model.FaqItem;
import ru.luxury.living.service.FaqService;

@RestController
@RequestMapping("faq")
@RequiredArgsConstructor
@Tag(name = "Операции с элементами FAQ панели")
public class FaqController {

    private final FaqService faqService;

    @PostMapping
    public FaqItem create(@Validated @RequestBody FaqItem faqItem) {
        return faqService.create(faqItem);
    }

    @PutMapping("{id}")
    public FaqItem update(@PathVariable Long id, @Validated @RequestBody FaqItem faqItem) {
        return faqService.update(id, faqItem);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        faqService.delete(id);
    }

    @GetMapping("{id}")
    public FaqItem getById(@PathVariable Long id) {
        return faqService.getById(id);
    }

    @GetMapping
    public Page<FaqItem> getAll(@ParameterObject @PageableDefault(sort = {"created"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return faqService.getAll(pageable);
    }
}
