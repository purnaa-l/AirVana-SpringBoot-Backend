package com.postgresql.aqi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "temperature_versus_aqi_prediction_records")
public class TemperatureVersusAQIPredictionRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double temperatureCelsius;
    private double windKph;
    private double pressureMb;
    private double humidity;
    private double cloud;
    private double uvIndex;
    private double predictedPm25;

    private LocalDateTime timestamp;

    @PrePersist
    public void setTimestamp() {
        this.timestamp = LocalDateTime.now();
    }

    public double getCloud() {
        return cloud;
    }

    public void setCloud(double cloud) {
        this.cloud = cloud;
    }

    public double getHumidity() {
        return humidity;
    }
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
    public double getPressureMb() {
        return pressureMb;
    }
    public void setPressureMb(double pressureMb) {
        this.pressureMb = pressureMb;
    }
    public double getPredictedPm25() {
        return predictedPm25;
    }
    public void setPredictedPm25(double predictedPm25) {
        this.predictedPm25 = predictedPm25;
    }
    public double getTemperatureCelsius() {
        return temperatureCelsius;
    }
    public void setTemperatureCelsius(double temperatureCelsius) {
        this.temperatureCelsius = temperatureCelsius;
    }
    public double getUvIndex() {
        return uvIndex;
    }
    public void setUvIndex(double uvIndex) {
        this.uvIndex = uvIndex;
    }
    public double getWindKph() {
        return windKph;
    }
    public void setWindKph(double windKph) {
        this.windKph = windKph;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
