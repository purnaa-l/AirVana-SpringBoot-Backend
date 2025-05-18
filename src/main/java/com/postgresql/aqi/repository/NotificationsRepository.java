package com.postgresql.aqi.repository;

import com.postgresql.aqi.entity.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationsRepository extends JpaRepository<Notifications, Long> {
    // String = type of primary key (email)
}
