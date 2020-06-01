package ru.itprogram.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssuedBookLite {

    @NotNull
    private Long bookId;

    @NotNull
    private Long readerId;

    @NotNull
    private Refund returnDate;

}
