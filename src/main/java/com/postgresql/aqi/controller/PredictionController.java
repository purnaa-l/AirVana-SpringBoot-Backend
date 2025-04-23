package com.postgresql.aqi.controller;

import com.postgresql.aqi.dto.PredictionRequestDTO;
import com.postgresql.aqi.dto.PredictionResponseDTO;
import com.postgresql.aqi.entity.TemperatureVersusAQIPredictionRecord;
import com.postgresql.aqi.service.TemperatureVersusAQIPredictionRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class PredictionController {

    @Autowired
    private TemperatureVersusAQIPredictionRecordService predictionService;

    private static final Logger logger = LoggerFactory.getLogger(PredictionController.class);

    @PostMapping("/temperature-versus-aqi")
    public TemperatureVersusAQIPredictionRecord predictAndStore(@RequestBody PredictionRequestDTO request) {
        logger.info("Received Prediction Request: {}", request);
        double prediction = callPythonPredictionService(request);
        return buildAndSaveRecord(request, prediction);
    }

    private double callPythonPredictionService(PredictionRequestDTO request) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PredictionRequestDTO> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<PredictionResponseDTO> response = restTemplate.exchange(
                    "http://127.0.0.1:5000/predict",
                    HttpMethod.POST,
                    entity,
                    PredictionResponseDTO.class
            );

            // Log status code and check for successful response
            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                return response.getBody().getPrediction();
            } else {
                logger.error("Received error response from Flask service: " + response.getStatusCode() +
                        " with body: " + response.getBody());
                throw new RuntimeException("Failed to get valid response from Flask service");
            }
        } catch (Exception e) {
            logger.error("Error while calling Flask service: " + e.getMessage(), e);
            throw new RuntimeException("Failed to call Flask service", e);
        }
    }

    // Separate method to build and save the entity
    private TemperatureVersusAQIPredictionRecord buildAndSaveRecord(PredictionRequestDTO request, double prediction) {
        TemperatureVersusAQIPredictionRecord record = new TemperatureVersusAQIPredictionRecord();
        record.setTemperatureCelsius(request.getTemperatureCelsius());
        record.setWindKph(request.getWindKph());
        record.setPressureMb(request.getPressureMb());
        record.setHumidity(request.getHumidity());
        record.setCloud(request.getCloud());
        record.setUvIndex(request.getUvIndex());
        record.setPredictedPm25(prediction);
        return predictionService.saveData(record);
    }
}
