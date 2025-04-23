package com.postgresql.aqi.dto;

import lombok.Getter;
import lombok.Setter;


public class PredictionResponseDTO {
    private double prediction;

    public double getPrediction() {
        return prediction;
    }

    public void setPrediction(double prediction) {
        this.prediction = prediction;
    }
}
