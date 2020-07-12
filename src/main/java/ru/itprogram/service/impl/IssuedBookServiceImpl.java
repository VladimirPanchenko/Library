package ru.itprogram.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import ru.itprogram.aspect.Loggable;
import ru.itprogram.domain.dto.IssuedBook;
import ru.itprogram.domain.dto.Refund;
import ru.itprogram.domain.entity.BookEntity;
import ru.itprogram.domain.entity.IssuedBookEntity;
import ru.itprogram.domain.entity.ReaderEntity;
import ru.itprogram.exception.EntityNotFoundException;
import ru.itprogram.repository.BookRepository;
import ru.itprogram.repository.IssuedBookRepository;
import ru.itprogram.repository.ReaderRepository;
import ru.itprogram.service.IssuedBookService;

import java.time.LocalDate;
import java.util.List;

import static ru.itprogram.utils.MessageLog.WRITE_READER_AND_BOOK_DB;

@Slf4j
@Service
@RequiredArgsConstructor
public class IssuedBookServiceImpl implements IssuedBookService {

    private final IssuedBookRepository issuedBookRepository;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final MapperFacade mapperFacade;

    @Override
    public List<IssuedBook> getAllIssuedBook() {
        return mapperFacade.mapAsList(issuedBookRepository.findAll(), IssuedBook.class);
    }

    @Loggable
    @Override
    public void bookIssuance(Long bookId, Long readerId, Refund refund) {
        log.info(WRITE_READER_AND_BOOK_DB);
        IssuedBookEntity issuedBookEntity = new IssuedBookEntity();
        BookEntity book = bookRepository.findById(bookId).orElseThrow(EntityNotFoundException::new);
        ReaderEntity reader = readerRepository.findById(readerId).orElseThrow(EntityNotFoundException::new);
        reader.getBooksOnHand().add(book);

        issuedBookEntity.setBook(book);
        issuedBookEntity.setReader(reader);
        issuedBookEntity.setPublicationDate(LocalDate.now());
        issuedBookEntity.setReturnDate(refund.getValue());

        readerRepository.save(reader);
        issuedBookRepository.save(issuedBookEntity);
    }
}
