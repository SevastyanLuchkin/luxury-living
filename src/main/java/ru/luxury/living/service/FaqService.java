package ru.luxury.living.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.luxury.living.model.FaqItem;
import ru.luxury.living.repository.FaqRepository;

@Service
@RequiredArgsConstructor
public class FaqService {

    private final FaqRepository faqRepository;

    public FaqItem create(FaqItem faqItem) {
        return faqRepository.save(faqItem);
    }

    @Transactional
    public FaqItem update(Long id, FaqItem request) {
        FaqItem faqItem = faqRepository.findById(id).orElseThrow();
        request.setId(id);
        return faqRepository.save(request);
    }

    public Page<FaqItem> getAll(Pageable pageable) {
        return faqRepository.findAll(pageable);
    }

    public FaqItem getById(Long id) {
        return faqRepository.findById(id)
                .orElseThrow();
    }

    public void delete(Long id) {
        faqRepository.deleteById(id);
    }
}
