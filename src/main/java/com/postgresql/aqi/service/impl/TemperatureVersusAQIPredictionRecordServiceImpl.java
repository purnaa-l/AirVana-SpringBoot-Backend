package com.postgresql.aqi.service.impl;

import com.postgresql.aqi.entity.TemperatureVersusAQIPredictionRecord;
import com.postgresql.aqi.repository.TemperatureVersusAQIPredictionRecordRepository;
import com.postgresql.aqi.service.TemperatureVersusAQIPredictionRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TemperatureVersusAQIPredictionRecordServiceImpl implements TemperatureVersusAQIPredictionRecordService {

    @Autowired
    private TemperatureVersusAQIPredictionRecordRepository repository;

    @Override
    @Transactional
    public TemperatureVersusAQIPredictionRecord saveData(TemperatureVersusAQIPredictionRecord record) {
        return repository.save(record);
    }
}
