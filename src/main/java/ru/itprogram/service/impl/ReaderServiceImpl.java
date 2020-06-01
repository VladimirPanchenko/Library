package ru.itprogram.service.impl;

import lombok.RequiredArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import ru.itprogram.aspect.Loggable;
import ru.itprogram.domain.dto.Reader;
import ru.itprogram.domain.entity.ReaderEntity;
import ru.itprogram.repository.ReaderRepository;
import ru.itprogram.service.ReaderService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {

    private final ReaderRepository readerRepository;
    private final MapperFacade mapperFacade;

    @Override
    public List<Reader> getAllReaders() {
        return mapperFacade.mapAsList(readerRepository.findAll(), Reader.class);
    }

    @Loggable
    @Override
    public void createReader(Reader reader) {
        readerRepository.save(mapperFacade.map(reader, ReaderEntity.class));
    }

    @Loggable
    @Override
    public void updateReaderById(Long id, Reader reader) {
        ReaderEntity updateReader = mapperFacade.map(reader, ReaderEntity.class);
        updateReader.setId(id);
        readerRepository.save(updateReader);
    }

}
