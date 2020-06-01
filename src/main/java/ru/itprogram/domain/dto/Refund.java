package ru.itprogram.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

import static ru.itprogram.domain.dto.Refund.Constants.SEVEN_DAYS;
import static ru.itprogram.domain.dto.Refund.Constants.TWELVE_DAYS;
import static ru.itprogram.domain.dto.Refund.Constants.FOURTEEN_DAYS;

@Getter
@AllArgsConstructor
public enum Refund {
    MINIMUM(LocalDate.now().plusDays(SEVEN_DAYS)),
    STANDARDLY(LocalDate.now().plusDays(TWELVE_DAYS)),
    MAXIMUM(LocalDate.now().plusDays(FOURTEEN_DAYS));

    private LocalDate value;

    static class Constants {
        public static final Long SEVEN_DAYS = 7l;
        public static final Long TWELVE_DAYS = 12l;
        public static final Long FOURTEEN_DAYS = 14l;
    }
}
