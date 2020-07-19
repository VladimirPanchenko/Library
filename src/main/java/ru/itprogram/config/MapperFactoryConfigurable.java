package ru.itprogram.config;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.ConverterFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.context.annotation.Configuration;
import ru.itprogram.domain.dto.Book;
import ru.itprogram.domain.dto.IssuedBook;
import ru.itprogram.domain.dto.Reader;
import ru.itprogram.domain.dto.User;
import ru.itprogram.domain.entity.BookEntity;
import ru.itprogram.domain.entity.IssuedBookEntity;
import ru.itprogram.domain.entity.ReaderEntity;
import ru.itprogram.domain.entity.UserEntity;

@Configuration
public class MapperFactoryConfigurable extends ConfigurableMapper {

    @Override
    protected void configure(MapperFactory factory) {
        factory.classMap(Book.class, BookEntity.class).byDefault();
        factory.classMap(IssuedBook.class, IssuedBookEntity.class).byDefault();
        factory.classMap(Reader.class, ReaderEntity.class).byDefault();
        factory.classMap(User.class, UserEntity.class);

        ConverterFactory converterFactory = factory.getConverterFactory();
        converterFactory.registerConverter(new UserConverter());
    }
}
