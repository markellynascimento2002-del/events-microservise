package com.markelly.events_microservise.repositories;

import com.markelly.events_microservise.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}