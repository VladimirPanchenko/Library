package ru.itprogram.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import ru.itprogram.aspect.LoggableBefore;
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

import static ru.itprogram.utils.MessageCode.ENTITY_NOT_FOUND;
import static ru.itprogram.utils.MessageLog.WRITE_READER_AND_BOOK_DB;

@Slf4j
@Service
@RequiredArgsConstructor
public class IssuedBookServiceImpl implements IssuedBookService {

    private final IssuedBookRepository issuedBookRepository;
    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;
    private final MapperFacade mapperFacade;
    private final ResourceBundleMessageSource messageSource;

    @Override
    public List<IssuedBook> getAllIssuedBook() {
        return mapperFacade.mapAsList(issuedBookRepository.findAll(), IssuedBook.class);
    }

    @LoggableBefore
    @Override
    public void bookIssuance(Long bookId, Long readerId, Refund refund) {
        log.info(WRITE_READER_AND_BOOK_DB);
        IssuedBookEntity issuedBookEntity = new IssuedBookEntity();
        BookEntity book = bookRepository
                .findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException(messageSource
                        .getMessage(ENTITY_NOT_FOUND, null, LocaleContextHolder.getLocale())));
        ReaderEntity reader = readerRepository
                .findById(readerId)
                .orElseThrow(() -> new EntityNotFoundException(messageSource
                        .getMessage(ENTITY_NOT_FOUND, null, LocaleContextHolder.getLocale())));
        reader.getBooksOnHand().add(book);

        issuedBookEntity.setBook(book);
        issuedBookEntity.setReader(reader);
        issuedBookEntity.setPublicationDate(LocalDate.now());
        issuedBookEntity.setReturnDate(refund.getValue());

        readerRepository.save(reader);
        issuedBookRepository.save(issuedBookEntity);
    }
}
