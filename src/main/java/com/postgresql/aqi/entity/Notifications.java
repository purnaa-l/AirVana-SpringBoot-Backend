package com.postgresql.aqi.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "notifications")
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate primary key values
    @Column(name = "contact_id")
    private long contactId;

    @Column(name = "email", nullable = false, length = 150)
    private String email;

    @Column(name = "city", nullable = false, length = 100)
    private String city;
    @Column(name = "aqi_threshold", nullable = false)
    private int aqiThreshold;
    @Column(name = "frequency", nullable = false, length = 50)
    private String frequency;

    public Notifications() {
    }

    public Notifications(String email, String city, int aqiThreshold, String frequency) {
        this.email = email;
        this.city = city;
        this.aqiThreshold = aqiThreshold;
        this.frequency = frequency;
    }

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAqiThreshold() {
        return aqiThreshold;
    }

    public void setAqiThreshold(int aqiThreshold) {
        this.aqiThreshold = aqiThreshold;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }
}
