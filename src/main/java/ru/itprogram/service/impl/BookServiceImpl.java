package ru.itprogram.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import ru.itprogram.aspect.Loggable;
import ru.itprogram.domain.dto.Book;
import ru.itprogram.domain.entity.BookEntity;
import ru.itprogram.repository.BookRepository;
import ru.itprogram.service.BookService;

import java.util.List;

import static ru.itprogram.utils.MessageLog.WRITE_BOOK_DB;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final MapperFacade mapperFacade;


    @Override
    public List<Book> getAllBooks() {
        return mapperFacade.mapAsList(bookRepository.findAll(), Book.class);
    }

    @Loggable
    @Override
    public void createBook(Book book) {
        log.info(WRITE_BOOK_DB, book);
        bookRepository.save(mapperFacade.map(book, BookEntity.class));
    }

    @Loggable
    @Override
    public void updateBookById(Long id, Book book) {
        log.info(WRITE_BOOK_DB, book);
        BookEntity updateBook = mapperFacade.map(book, BookEntity.class);
        updateBook.setId(id);
        bookRepository.save(updateBook);
    }
}
