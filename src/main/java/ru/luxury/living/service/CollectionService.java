package ru.luxury.living.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.luxury.living.model.Collection;
import ru.luxury.living.repository.CollectionRepository;

@Service
@RequiredArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionRepository;

    public Page<Collection> findAll(Pageable pageable) {
        return collectionRepository.findAll(pageable);
    }

    public Collection create(Collection collection) {
        return collectionRepository.save(collection);
    }

    public void delete(Long id) {
        collectionRepository.deleteById(id);
    }

    public Collection update(Collection collection) {
        collectionRepository.findById(collection.getId()).orElseThrow();
        return collectionRepository.save(collection);
    }
}
