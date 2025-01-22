package com.movewise.movewise_api.Model.Converter;

import java.time.Duration;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DurationAttributeConverter implements AttributeConverter<Duration, Long> {

    @Override
    public Long convertToDatabaseColumn(Duration duration) {
        return duration == null ? null : duration.toMinutes();
    }

    @Override
    public Duration convertToEntityAttribute(Long dbData) {
        return dbData == null ? null : Duration.ofMinutes(dbData);
    }
}
