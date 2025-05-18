package com.postgresql.aqi.controller;

import com.postgresql.aqi.entity.Notifications;
import com.postgresql.aqi.service.impl.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin(origins = "*") // allow frontend access
public class NotificationsController {

    @Autowired
    private NotificationsService service;

    @PostMapping("/subscribe")
    public ResponseEntity<?> subscribe(@RequestBody Notifications notification) {
        Notifications saved = service.subscribe(notification);
        return ResponseEntity.ok(saved);
    }

}
