package com.kodilla.controller;

import com.kodilla.domain.Title;
import com.kodilla.dto.TitleDto;
import com.kodilla.exception.TitleNotFoundException;
import com.kodilla.mapper.TitleMapper;
import com.kodilla.service.TitleDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/titles")
@RequiredArgsConstructor
public class TitleController {

    private final TitleMapper titleMapper;
    private final TitleDbService titleDbService;

    @GetMapping
    public ResponseEntity<List<TitleDto>> getTitles() {
        List<Title> titles = titleDbService.getTitles();
        return ResponseEntity.ok(titleMapper.mapToTitleDtoList(titles));
    }

    @GetMapping("{titleId}")
    public ResponseEntity<TitleDto> getTitle(@PathVariable int titleId) throws TitleNotFoundException {
        return ResponseEntity.ok(titleMapper.mapToTitleDto(titleDbService.getTitle(titleId)));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTitle(@RequestBody TitleDto titleDto) {
        Title title = titleMapper.mapToTitle(titleDto);
        titleDbService.saveTitle(title);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<TitleDto> updateTitle(@RequestBody TitleDto titleDto) {
        Title title = titleMapper.mapToTitle(titleDto);
        Title savedReader = titleDbService.saveTitle(title);
        return ResponseEntity.ok(titleMapper.mapToTitleDto(savedReader));
    }

    @DeleteMapping("{titleId}")
    public ResponseEntity<Void> deleteTitle(@PathVariable int titleId) throws TitleNotFoundException {
        Optional<Title> title = Optional.ofNullable(titleDbService.getTitle(titleId));
        if (title.isPresent()) {
            titleDbService.deleteById(titleId);
            return ResponseEntity.ok().build();
        } else {
            throw new TitleNotFoundException();
        }
    }
}
