package ru.luxury.living.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.luxury.living.model.Type;
import ru.luxury.living.repository.TypeRepository;

@Service
@RequiredArgsConstructor
public class TypeService {

    private final TypeRepository typeRepository;

    public Type create(Type type) {
        return typeRepository.save(type);
    }

    public Type getById(Long id) {
        return typeRepository.findById(id).orElseThrow();
    }

    public Type update(Long id, Type type) {
        typeRepository.findById(id).orElseThrow();
        type.setId(id);
        return typeRepository.save(type);
    }

    public void delete(Long id) {
        typeRepository.deleteById(id);
    }

    public Page<Type> findAll(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }
}
