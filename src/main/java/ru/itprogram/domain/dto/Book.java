package ru.itprogram.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private Long id;

    @NotEmpty
    private String title;

    @NotEmpty
    private String authorEntity;

    @NotNull
    private LocalDate publicationDate;

    @NotEmpty
    private String publishingHouse;

}
