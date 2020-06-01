package ru.itprogram.config;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

public class LocalDatePropertyConverter implements Converter<String, LocalDate> {

    @Override
    public LocalDate convert(String s) {
        return LocalDate.parse(s);
    }

}
