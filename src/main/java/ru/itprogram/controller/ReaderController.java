package ru.itprogram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itprogram.domain.dto.Reader;
import ru.itprogram.service.ReaderService;

import java.util.List;

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
    public ResponseEntity<HttpStatus> addReader(@RequestBody Reader reader) {
        readerService.createReader(reader);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<HttpStatus> updateReader(@RequestParam Long id, Reader reader) {
        readerService.updateReaderById(id, reader);
        return ResponseEntity.ok().build();
    }

}
