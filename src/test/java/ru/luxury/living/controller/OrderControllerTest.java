package ru.luxury.living.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.luxury.living.model.Order;
import ru.luxury.living.model.OrderRequest;
import ru.luxury.living.model.Product;
import ru.luxury.living.repository.OrderRepository;
import ru.luxury.living.repository.ProductRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {
    @Mock
    private OrderRepository orders;
    @Mock
    private ProductRepository products;
    private OrderController controller;

    @BeforeEach
    void setUp() {
        controller = new OrderController(orders, products);
    }

    @Test
    void createRejectsBlankContactDetails() {
        OrderRequest request = new OrderRequest();
        request.setEmail("  ");
        request.setPhone("\t");

        ResponseEntity<String> response = controller.create(request, false);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Телефон либо электронная почта должны быть заданы", response.getBody());
        verifyNoInteractions(orders, products);
    }

    @Test
    void createCurtainOrderDoesNotRequireProduct() {
        OrderRequest request = new OrderRequest();
        request.setPhone("123");

        assertEquals(HttpStatus.OK, controller.create(request, true).getStatusCode());

        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
        verify(orders).save(captor.capture());
        assertTrue(captor.getValue().getCurtains());
        assertEquals("123", captor.getValue().getPhone());
        assertNull(captor.getValue().getProductId());
        verifyNoInteractions(products);
    }

    @Test
    void createRejectsProductOrderWithoutProductId() {
        OrderRequest request = new OrderRequest();
        request.setEmail("person@example.com");

        ResponseEntity<String> response = controller.create(request, false);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Не задан товар", response.getBody());
        verifyNoInteractions(orders, products);
    }

    @Test
    void createSavesOrderWhenProductExists() {
        OrderRequest request = new OrderRequest();
        request.setEmail("person@example.com");
        request.setProductId(7L);
        when(products.findById(7L)).thenReturn(Optional.of(new Product()));

        assertEquals(HttpStatus.OK, controller.create(request, null).getStatusCode());

        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
        verify(orders).save(captor.capture());
        assertEquals(7L, captor.getValue().getProductId());
        assertEquals("person@example.com", captor.getValue().getEmail());
    }

    @Test
    void createDoesNotSaveOrderWhenProductIsMissing() {
        OrderRequest request = new OrderRequest();
        request.setPhone("123");
        request.setProductId(7L);
        when(products.findById(7L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> controller.create(request, false));
        verifyNoInteractions(orders);
    }

    @Test
    void updatePreservesFieldsNotPresentInRequest() {
        Order existing = new Order().setHandled(false).setPhone("123").setEmail("old@example.com").setProductId(7L);
        OrderRequest request = new OrderRequest();
        request.setHandled(true);
        request.setEmail("new@example.com");
        when(orders.findById(1L)).thenReturn(Optional.of(existing));
        when(orders.save(existing)).thenReturn(existing);

        assertSame(existing, controller.update(1L, request).getBody());
        assertTrue(existing.getHandled());
        assertEquals("new@example.com", existing.getEmail());
        assertEquals("123", existing.getPhone());
        assertEquals(7L, existing.getProductId());
    }
}
