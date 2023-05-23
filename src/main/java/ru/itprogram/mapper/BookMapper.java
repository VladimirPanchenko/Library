package ru.itprogram.mapper;

import org.mapstruct.Mapper;
import ru.itprogram.domain.dto.Book;
import ru.itprogram.domain.entity.BookEntity;

import java.util.List;

@Mapper
public interface BookMapper {
    Book getBook(BookEntity bookEntity);
    BookEntity getBookEntity(Book book);
    List<Book> mapAsListBook(List<BookEntity> bookEntities);
    List<BookEntity> mapAsListBookEntity(List<Book> books);
}
