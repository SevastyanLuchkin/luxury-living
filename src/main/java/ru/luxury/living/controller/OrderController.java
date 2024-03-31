package ru.luxury.living.controller;

import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.luxury.living.model.Order;
import ru.luxury.living.model.OrderRequest;
import ru.luxury.living.model.OrderResponse;
import ru.luxury.living.model.Product;
import ru.luxury.living.model.ProductResponse;
import ru.luxury.living.repository.OrderRepository;
import ru.luxury.living.repository.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<String> create(@RequestBody OrderRequest request) {
        if (!StringUtils.hasText(request.getEmail()) && !StringUtils.hasText(request.getPhone())) {
            return ResponseEntity.badRequest().body("Телефон либо электронная почта должны быть заданы");
        }
        productRepository.findById(request.getProductId()).orElseThrow();
        orderRepository.save(
                new Order()
                        .setProductId(request.getProductId())
                        .setEmail(request.getEmail())
                        .setPhone(request.getPhone())
        );
        return ResponseEntity.ok("OK");
    }

    @PutMapping("{orderId}")
    public ResponseEntity<?> update(@PathVariable long orderId, OrderRequest request) {
        if (!StringUtils.hasText(request.getEmail()) && !StringUtils.hasText(request.getPhone())) {
            return ResponseEntity.badRequest().body("Телефон либо электронная почта должны быть заданы");
        }
        Order order = orderRepository.findById(orderId).orElseThrow()
                .setHandled(request.getHandled())
                .setEmail(request.getEmail())
                .setPhone(request.getPhone());
        return ResponseEntity.ok(orderRepository.save(order));
    }

    @DeleteMapping("{orderId}")
    public void delete(@PathVariable long orderId) {
        orderRepository.deleteById(orderId);
    }

    @GetMapping
    public Page<OrderResponse> findAll(@ParameterObject @PageableDefault(sort = {"created"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Order> ordersPage = orderRepository.findAll(pageable);
        List<OrderResponse> orders = ordersPage.getContent().stream()
                .map(this::mapOrder)
                .toList();

        return new PageImpl<>(orders, pageable, ordersPage.getTotalElements());
    }

    private OrderResponse mapOrder(Order order) {
        Product product = productRepository.findById(order.getProductId()).orElseThrow();
        ProductResponse productResponse = new ProductResponse()
                .setId(product.getId())
                .setArticle(product.getArticle())
                .setPrice(product.getPrice())
                .setInStock(product.getInStock())
                .setTitle(product.getTitle())
                .setDescription(product.getDescription())
                .setImageId(product.getImageId())
                .setImageIds(product.getImageIds())
                .setActive(product.getActive())
                .setCountry(product.getCountry())
                .setMaterials(product.getMaterials())
                .setVolume(product.getVolume());
        return new OrderResponse()
                .setProduct(productResponse)
                .setHandled(order.getHandled())
                .setEmail(order.getEmail())
                .setPhone(order.getPhone());
    }
}
