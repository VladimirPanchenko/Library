package ru.itprogram.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itprogram.domain.dto.Reader;
import ru.itprogram.service.ReaderService;

import javax.validation.Valid;
import java.util.List;

import static ru.itprogram.utils.MessageLog.CREATE_READER;
import static ru.itprogram.utils.MessageLog.UPDATE_READER;

@Slf4j
@RestController
@RequestMapping("/readers")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderService readerService;

    @GetMapping
    public ResponseEntity<List<Reader>> getReaders() {
        return ResponseEntity.ok().body(readerService.getAllReaders());
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addReader(@Valid @RequestBody Reader reader) {
        log.info(CREATE_READER, reader);
        readerService.createReader(reader);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateReader(@RequestParam Long id,@Valid Reader reader) {
        log.info(UPDATE_READER, reader);
        readerService.updateReaderById(id, reader);
        return ResponseEntity.ok().build();
    }

}
