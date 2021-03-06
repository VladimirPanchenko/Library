package ru.itprogram.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itprogram.domain.dto.IssuedBook;
import ru.itprogram.domain.dto.IssuedBookLite;
import ru.itprogram.service.IssuedBookService;

import javax.validation.Valid;
import java.util.List;

import static ru.itprogram.utils.MessageLog.BOOK_ISSUANCE;

@Slf4j
@RestController
@RequestMapping("/issuedBooks")
@RequiredArgsConstructor
public class IssuedBookController {

    private final IssuedBookService issuedBookService;

    @GetMapping
    public ResponseEntity<List<IssuedBook>> getIssuedBook() {
        return ResponseEntity.ok().body(issuedBookService.getAllIssuedBook());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> bookIssue(@Valid @RequestBody IssuedBookLite issuedBookLite) {
        log.info(BOOK_ISSUANCE, issuedBookLite);
        issuedBookService.bookIssuance(issuedBookLite.getBookId(),
                issuedBookLite.getReaderId(), issuedBookLite.getReturnDate());
        return ResponseEntity.ok().build();
    }

}
