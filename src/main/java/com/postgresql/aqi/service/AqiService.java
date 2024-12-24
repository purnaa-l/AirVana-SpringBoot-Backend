package com.postgresql.aqi.service;

import com.postgresql.aqi.dto.AqiDto;

import java.util.List;

public interface AqiService {
    AqiDto createAqi(AqiDto aqiDto);
    AqiDto getAqi(Long aqiId);
    List<AqiDto> getAllAQIs();
    void deleteAQI(Long aqiId);
}
