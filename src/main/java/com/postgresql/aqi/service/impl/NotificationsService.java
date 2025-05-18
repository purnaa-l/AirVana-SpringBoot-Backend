package com.postgresql.aqi.service.impl;

import com.postgresql.aqi.entity.Notifications;
import com.postgresql.aqi.repository.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationsService {

    @Autowired
    private NotificationsRepository repository;

    public Notifications subscribe(Notifications notification) {
        return repository.save(notification); // Upsert
    }
}
