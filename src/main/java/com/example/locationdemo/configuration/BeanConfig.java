package com.example.locationdemo.configuration;

import org.hibernate.collection.spi.PersistentCollection;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class BeanConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        AbstractConverter<Long, LocalDateTime> longConverter = new AbstractConverter<Long, LocalDateTime>() {
            @Override
            protected LocalDateTime convert(Long source) {
                LocalDateTime localDateTime = source == null ? null : LocalDateTime.ofInstant(Instant.ofEpochMilli(source), ZoneOffset.UTC);
                return localDateTime;
            }
        };

        AbstractConverter<LocalDateTime, Long> localDateTimeConverter = new AbstractConverter<LocalDateTime, Long>() {
            @Override
            protected Long convert(LocalDateTime source) {
                return source == null ? null : source.toEpochSecond(ZoneOffset.UTC) * 1000;
            }
        };
        modelMapper.addConverter(longConverter);
        modelMapper.addConverter(localDateTimeConverter);
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.getConfiguration().setPropertyCondition(new Condition<Object, Object>() {
            public boolean applies(MappingContext<Object, Object> context) {
                return !(context.getSource() instanceof PersistentCollection);
            }
        });
        return modelMapper;
    }
}
