package ru.luxury.living.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import ru.luxury.living.model.Product;
import ru.luxury.living.repository.ProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository repository;
    private ProductService service;
    private final Pageable pageable = PageRequest.of(1, 10);

    @BeforeEach
    void setUp() {
        service = new ProductService(repository);
    }

    @Test
    void searchWithoutFiltersReturnsOnlyActiveProductsForNonAdmin() {
        Page<Product> expected = new PageImpl<>(List.of(new Product()));
        when(repository.findAllByActive(true, pageable)).thenReturn(expected);

        assertSame(expected, service.search(null, null, null, null, null, null, pageable));
        verify(repository, never()).findProducts(any(), any(), any(), any(), any(), any(), any());
    }

    @ParameterizedTest
    @CsvSource({"true, true", "false, false"})
    void filteredSearchUsesTheAppropriateActiveStates(boolean admin, boolean includesInactive) {
        List<Long> brands = List.of(1L);
        List<Long> categories = List.of(2L);
        List<Long> collections = List.of(3L);
        List<Boolean> states = includesInactive ? ProductService.ALL : ProductService.ACTIVE;
        Page<Product> expected = new PageImpl<>(List.of(new Product()));
        when(repository.findProducts(brands, categories, collections, 4L, true, states, pageable)).thenReturn(expected);

        assertSame(expected, service.search(brands, categories, collections, 4L, true, admin, pageable));
    }

    @Test
    void textSearchReturnsTitleMatchesWithoutSearchingDescriptions() {
        Page<Product> matches = new PageImpl<>(List.of(new Product()));
        when(repository.findAllByTitleLike("chair", ProductService.ACTIVE, pageable)).thenReturn(matches);

        assertSame(matches, service.textSearch("chair", false, pageable));
        verify(repository, never()).findAllByDescriptionLike(anyString(), any());
    }

    @Test
    void textSearchFallsBackToDescriptionWhenTitleHasNoMatches() {
        Page<Product> matches = new PageImpl<>(List.of(new Product()));
        when(repository.findAllByTitleLike("chair", ProductService.ALL, pageable)).thenReturn(Page.empty());
        when(repository.findAllByDescriptionLike("chair", pageable)).thenReturn(matches);

        assertSame(matches, service.textSearch("chair", true, pageable));
    }

    @Test
    void findAllSortsByBrandNumberThenTitle() {
        PageRequest expected = PageRequest.of(1, 10, Sort.by("brand.number").and(Sort.by("title")));
        when(repository.findAll(expected)).thenReturn(Page.empty());

        service.findAll(pageable);

        verify(repository).findAll(expected);
    }

    @Test
    void updateAssignsPathIdBeforeSaving() {
        Product request = new Product().setTitle("New title");
        request.setId(99L);
        when(repository.findById(1L)).thenReturn(Optional.of(new Product()));
        when(repository.save(request)).thenReturn(request);

        assertSame(request, service.update(1L, request));
        assertEquals(1L, request.getId());
    }

    @Test
    void updateDoesNotSaveMissingProduct() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.update(1L, new Product()));
        verify(repository, never()).save(any());
    }

    @Test
    void setLikedUpdatesExistingProduct() {
        Product product = new Product().setLiked(false);
        when(repository.findById(1L)).thenReturn(Optional.of(product));

        service.setLiked(1L, true);

        assertTrue(product.getLiked());
    }
}
