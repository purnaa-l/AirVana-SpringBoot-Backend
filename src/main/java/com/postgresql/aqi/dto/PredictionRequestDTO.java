package com.postgresql.aqi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PredictionRequestDTO {
    private double temperatureCelsius;
    private double windKph;
    private double pressureMb;
    private double humidity;
    private double cloud;
    private double uvIndex;

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
}

