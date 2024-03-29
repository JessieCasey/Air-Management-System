package com.air.managment.system.common.convertor;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;

/**
 * This custom {@link DurationToLongConverter} optimizes database memory usage when storing {@link Duration} objects.
 * By default, {@link Duration} values are stored in nanoseconds. However, in this modified version, they're stored in seconds.
 * It will reduce storage requirements and optimize database.
 */

@Converter(autoApply = true)
public class DurationToLongConverter implements AttributeConverter<Duration, Long> {

    @Override
    public Long convertToDatabaseColumn(Duration duration) {
        return duration.toSeconds();
    }

    @Override
    public Duration convertToEntityAttribute(Long seconds) {
        return Duration.ofSeconds(seconds);
    }
}
