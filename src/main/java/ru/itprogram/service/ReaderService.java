package ru.itprogram.service;

import ru.itprogram.domain.dto.Reader;

import java.util.List;

public interface ReaderService {

    List<Reader> getAllReaders();

    void createReader(Reader reader);

    void updateReaderById(Long id, Reader reader);

}
