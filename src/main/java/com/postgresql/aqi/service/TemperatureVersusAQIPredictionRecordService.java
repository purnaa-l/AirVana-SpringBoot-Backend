package com.postgresql.aqi.service;

import com.postgresql.aqi.entity.TemperatureVersusAQIPredictionRecord;

public interface TemperatureVersusAQIPredictionRecordService {
    TemperatureVersusAQIPredictionRecord saveData(TemperatureVersusAQIPredictionRecord record);
}
