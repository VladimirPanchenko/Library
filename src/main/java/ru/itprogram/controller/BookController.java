package ru.itprogram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itprogram.domain.dto.Book;
import ru.itprogram.service.BookService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getBooks() {
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addBook(@Valid @RequestBody Book book) {
        bookService.createBook(book);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateBook(@RequestParam Long id, @Valid @RequestBody Book book) {
        bookService.updateBookById(id, book);
        return ResponseEntity.ok().build();
    }

}
