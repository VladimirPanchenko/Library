package ru.itprogram.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reader {

    private Long id;

    @NotEmpty
    private String fio;

    private List<Book> booksOnHand;

}
