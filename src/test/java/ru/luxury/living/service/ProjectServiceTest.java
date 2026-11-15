package ru.luxury.living.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.luxury.living.model.Project;
import ru.luxury.living.model.ProjectRequest;
import ru.luxury.living.repository.ProjectRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {
    @Mock
    private ProjectRepository repository;
    private ProjectService service;

    @BeforeEach
    void setUp() {
        service = new ProjectService(repository);
    }

    @Test
    void createCopiesAllRequestFields() {
        ProjectRequest request = new ProjectRequest();
        request.setTitle("Title");
        request.setDescription("Description");
        request.setImageId(1L);
        request.setImageIds(new Long[]{2L, 3L});
        request.setNumber(4);
        when(repository.save(any(Project.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Project result = service.create(request);

        assertEquals("Title", result.getTitle());
        assertEquals("Description", result.getDescription());
        assertEquals(1L, result.getImageId());
        assertArrayEquals(new Long[]{2L, 3L}, result.getImageIds());
        assertEquals(4, result.getNumber());
        verify(repository).save(result);
    }

    @Test
    void updateChangesOnlyFieldsProvidedInRequest() {
        Project existing = new Project().setTitle("Old").setDescription("Keep")
                .setImageId(1L).setImageIds(new Long[]{2L}).setNumber(3);
        ProjectRequest request = new ProjectRequest();
        request.setTitle("New");
        request.setImageIds(new Long[0]);
        request.setNumber(0);
        when(repository.findById(5L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        assertSame(existing, service.update(5L, request));
        assertEquals("New", existing.getTitle());
        assertEquals("Keep", existing.getDescription());
        assertEquals(1L, existing.getImageId());
        assertArrayEquals(new Long[0], existing.getImageIds());
        assertEquals(0, existing.getNumber());
    }

    @Test
    void updateCanReplaceDescriptionAndImage() {
        Project existing = new Project().setDescription("Old").setImageId(1L);
        ProjectRequest request = new ProjectRequest();
        request.setDescription("");
        request.setImageId(2L);
        when(repository.findById(5L)).thenReturn(Optional.of(existing));
        when(repository.save(existing)).thenReturn(existing);

        service.update(5L, request);

        assertEquals("", existing.getDescription());
        assertEquals(2L, existing.getImageId());
    }

    @Test
    void updateDoesNotSaveMissingProject() {
        when(repository.findById(5L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> service.update(5L, new ProjectRequest()));
        verify(repository, never()).save(any());
    }
}
