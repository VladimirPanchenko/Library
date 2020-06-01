package ru.itprogram.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itprogram.domain.entity.BookEntity;
import ru.itprogram.domain.entity.ReaderEntity;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssuedBook {

    private Long id;
    private BookEntity book;
    private ReaderEntity reader;
    private LocalDate publicationDate;
    private LocalDate returnDate;

}
