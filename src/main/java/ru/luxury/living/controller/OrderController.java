package ru.luxury.living.controller;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.luxury.living.model.Order;
import ru.luxury.living.model.OrderRequest;
import ru.luxury.living.repository.OrderRepository;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody OrderRequest request) {
        if (!StringUtils.hasText(request.getEmail()) && !StringUtils.hasText(request.getPhone())) {
            return ResponseEntity.badRequest().body("Телефон либо электронная почта должны быть заданы");
        }
        orderRepository.save(
                new Order()
                        .setProductId(request.getProductId())
                        .setEmail(request.getEmail())
                        .setPhone(request.getPhone())
        );
        return ResponseEntity.ok("OK");
    }

    @GetMapping
    public Page<Order> findAll(@ParameterObject @PageableDefault(sort = {"created"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return orderRepository.findAll(pageable);
    }
}
