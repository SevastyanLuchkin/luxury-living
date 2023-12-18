package ru.luxury.living.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.luxury.living.model.EmailSubscription;

public interface EmailSubscriptionRepository extends JpaRepository<EmailSubscription, Long> {
}
