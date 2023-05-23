package ru.itprogram.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itprogram.aspect.LoggableAfter;
import ru.itprogram.aspect.LoggableAround;
import ru.itprogram.domain.dto.Book;
import ru.itprogram.domain.entity.BookEntity;
import ru.itprogram.mapper.BookMapper;
import ru.itprogram.repository.BookRepository;
import ru.itprogram.service.BookService;

import java.util.List;

import static ru.itprogram.utils.MessageLog.WRITE_BOOK_DB;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper mapperFacade;
    @Override
    public List<Book> getAllBooks() {
        return mapperFacade.mapAsListBook(bookRepository.findAll());
    }
    @LoggableAfter
    @LoggableAround
    @Override
    public void createBook(Book book) {
        log.info(WRITE_BOOK_DB, book);
        bookRepository.save(mapperFacade.getBookEntity(book));
    }
    @LoggableAfter
    @Override
    public void updateBookById(Long id, Book book) {
        log.info(WRITE_BOOK_DB, book);
        BookEntity updateBook = mapperFacade.getBookEntity(book);
        updateBook.setId(id);
        bookRepository.save(updateBook);
    }
}
