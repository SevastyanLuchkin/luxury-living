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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.luxury.living.model.EmailSubscription;
import ru.luxury.living.service.EmailSubscriptionService;

@RestController
@RequiredArgsConstructor
@Tag(name = "Операции с подпиской")
@RequestMapping("email-subscription")
public class EmailSubscriptionController {

    private final EmailSubscriptionService emailSubscriptionService;

    @PostMapping
    public EmailSubscription create(@Validated @RequestBody EmailSubscription email) {
        return emailSubscriptionService.create(email);
    }

    @PutMapping("{id}/change-activity")
    public EmailSubscription update(@PathVariable Long id, @RequestParam Boolean status) {
        return emailSubscriptionService.update(id, status);
    }

    @DeleteMapping
    public void delete(Long id) {
        emailSubscriptionService.delete(id);
    }

    @GetMapping
    public Page<EmailSubscription> getAll(@ParameterObject @PageableDefault(sort = {"created"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return emailSubscriptionService.getAll(pageable);
    }
}
