package ru.luxury.living.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.luxury.living.model.Brand;
import ru.luxury.living.repository.BrandRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public Brand create(Brand brand) {
        return brandRepository.save(brand);
    }

    @Transactional
    public Brand update(Long id, Brand request) {
        brandRepository.findById(id)
                .orElseThrow();
        request.setId(id);
        return brandRepository.save(request);
    }

    public Page<Brand> findAll(Pageable pageable) {
        return brandRepository.findAll(pageable);
    }

    public Brand getById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow();
    }

    public void delete(Long id) {
        brandRepository.deleteById(id);
    }

    public List<Brand> findAllByIds(Iterable<Long> ids) {
        return brandRepository.findAllById(ids);
    }
}
