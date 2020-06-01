package ru.itprogram.service;

import ru.itprogram.domain.dto.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    void createBook(Book book);

    void updateBookById(Long id, Book book);
}
