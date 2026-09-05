package ru.luxury.living.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.luxury.living.model.Brand;
import ru.luxury.living.repository.BrandRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final Environment springEnv;

    public Brand create(Brand brand) {
        return brandRepository.save(brand);
    }

    @PostConstruct
    public void init() {
        Map<String, Object> props = new HashMap<>();
        if (springEnv instanceof AbstractEnvironment) {
            for (PropertySource<?> source : ((AbstractEnvironment) springEnv).getPropertySources()) {
                if (source instanceof EnumerablePropertySource) {
                    for (String key : ((EnumerablePropertySource<?>) source).getPropertyNames()) {
                        props.put(key, springEnv.getProperty(key));
                    }
                }
            }
        }
        System.out.println(props);
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
