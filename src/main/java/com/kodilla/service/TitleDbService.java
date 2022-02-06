package com.kodilla.service;

import com.kodilla.domain.Title;
import com.kodilla.exception.TitleNotFoundException;
import com.kodilla.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TitleDbService {

    private final TitleRepository titleRepository;

    public List<Title> getTitles() {
        return titleRepository.findAll();
    }

    public Title getTitle(final int titleId) throws TitleNotFoundException {
        return titleRepository.findById(titleId).orElseThrow(TitleNotFoundException::new);
    }

    public Title getTitle(final String title) {
        return titleRepository.findByTitle(title);
    }

    public Title saveTitle(final Title title) {
        return titleRepository.save(title);
    }

    public void deleteById(final int titleId) {
        titleRepository.deleteById(titleId);
    }
}
