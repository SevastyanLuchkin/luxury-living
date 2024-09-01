package ru.luxury.living.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.luxury.living.client.EmailClient;
import ru.luxury.living.model.EmailSubscription;
import ru.luxury.living.props.EmailProperties;
import ru.luxury.living.repository.EmailSubscriptionRepository;

@Service
@RequiredArgsConstructor
public class EmailSubscriptionService {

    private final EmailSubscriptionRepository emailSubscriptionRepository;
    private final EmailClient emailClient;
    private final EmailProperties emailProperties;

    public EmailSubscription create(EmailSubscription email) {
        EmailSubscription emailSubscription = emailSubscriptionRepository.save(email);
        try {
            emailClient.subscribe(emailProperties.getKey(), email.getEmail());
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new RuntimeException("Не удалось подписаться");
        }
        return emailSubscription;
    }

    @Transactional
    public EmailSubscription update(Long id, Boolean status) {
        var emailSubscription = emailSubscriptionRepository.findById(id)
                .orElseThrow();
        emailSubscription.setActive(status);
        return emailSubscription;
    }

    public void delete(Long id) {
        emailSubscriptionRepository.deleteById(id);
    }

    public EmailSubscription getById(Long id) {
        return emailSubscriptionRepository.findById(id)
                .orElseThrow();
    }

    public Page<EmailSubscription> findAll(Pageable pageable) {
        return emailSubscriptionRepository.findAll(pageable);
    }
}
