package ru.itprogram.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @NotEmpty
    @Size(min = 4, max = 50)
    private String userName;

    @NotEmpty
    @Size(min = 6, max = 60)
    private String password;

    @NotEmpty
    @Size(min = 6, max = 60)
    private String passwordConfirm;

}
