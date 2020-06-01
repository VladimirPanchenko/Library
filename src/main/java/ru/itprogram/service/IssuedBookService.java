package ru.itprogram.service;

import ru.itprogram.domain.dto.IssuedBook;
import ru.itprogram.domain.dto.Refund;

import java.util.List;

public interface IssuedBookService {

    List<IssuedBook> getAllIssuedBook();

    void bookIssuance(Long bookId, Long readerId, Refund refund);

}
