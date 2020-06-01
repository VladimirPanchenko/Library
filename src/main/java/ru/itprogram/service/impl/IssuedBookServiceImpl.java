package ru.itprogram.service.impl;

import lombok.RequiredArgsConstructor;
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
