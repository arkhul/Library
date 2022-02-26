package com.kodilla.controller;

import com.kodilla.domain.Reader;
import com.kodilla.dto.ReaderDto;
import com.kodilla.exception.ReaderNotFoundException;
import com.kodilla.mapper.ReaderMapper;
import com.kodilla.service.ReaderDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/readers")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderDbService readerDbService;
    private final ReaderMapper readerMapper;

    @GetMapping
    public ResponseEntity<List<ReaderDto>> getReaders() {
        List<Reader> readers = readerDbService.getReaders();
        return ResponseEntity.ok(readerMapper.mapToReaderDtoList(readers));
    }

    @GetMapping("{readerId}")
    public ResponseEntity<ReaderDto> getReader(@PathVariable int readerId) throws ReaderNotFoundException {
        return ResponseEntity.ok(readerMapper.mapToReaderDto(readerDbService.getReader(readerId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createReader(@RequestBody ReaderDto readerDto) {
        Reader reader = readerMapper.mapToReader(readerDto);
        readerDbService.saveReader(reader);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<ReaderDto> updateReader(@RequestBody ReaderDto readerDto) {
        Reader reader = readerMapper.mapToReader(readerDto);
        Reader savedReader = readerDbService.saveReader(reader);
        return ResponseEntity.ok(readerMapper.mapToReaderDto(savedReader));
    }

    @DeleteMapping("{readerId}")
    public ResponseEntity<Void> deleteReader(@PathVariable int readerId) throws ReaderNotFoundException {
        Optional<Reader> reader = Optional.ofNullable(readerDbService.getReader(readerId));
        if (reader.isPresent()) {
            readerDbService.deleteById(readerId);
            return ResponseEntity.ok().build();
        } else {
            throw new ReaderNotFoundException();
        }
    }
}
