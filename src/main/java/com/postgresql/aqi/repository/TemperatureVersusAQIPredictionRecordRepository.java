package com.postgresql.aqi.repository;

import com.postgresql.aqi.entity.TemperatureVersusAQIPredictionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemperatureVersusAQIPredictionRecordRepository extends JpaRepository<TemperatureVersusAQIPredictionRecord, Long> {
    // Custom queries can be added here if needed
}
