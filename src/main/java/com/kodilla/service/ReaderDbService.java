package com.kodilla.service;

import com.kodilla.domain.Reader;
import com.kodilla.exception.ReaderNotFoundException;
import com.kodilla.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderDbService {

    private final ReaderRepository readerRepository;

    public List<Reader> getReaders() {
        return readerRepository.findAll();
    }

    public Reader getReader(final int readerId) throws ReaderNotFoundException {
        return readerRepository.findById(readerId).orElseThrow(ReaderNotFoundException::new);
    }

    public Reader saveReader(final Reader reader) {
        return readerRepository.save(reader);
    }

    public void deleteById(final int readerId) {
        readerRepository.deleteById(readerId);
    }
}
