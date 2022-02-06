package com.kodilla.controller;

import com.kodilla.domain.Copy;
import com.kodilla.domain.Title;
import com.kodilla.dto.CopyDto;
import com.kodilla.exception.CopyNotFoundException;
import com.kodilla.exception.TitleNotFoundException2;
import com.kodilla.mapper.CopyMapper;
import com.kodilla.service.CopyDbService;
import com.kodilla.service.TitleDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/copies")
@RequiredArgsConstructor
public class CopyController {

    private final CopyDbService copyDbService;
    private final CopyMapper copyMapper;
    private final TitleDbService titleDbService;

    @GetMapping
    public ResponseEntity<List<CopyDto>> getCopies() {
        List<Copy> copies = copyDbService.getCopies();
        return ResponseEntity.ok(copyMapper.mapToCopyDtoList(copies));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCopy(@RequestBody CopyDto copyDto) {
        Copy copy = copyMapper.mapToCopy(copyDto);
        copyDbService.saveCopy(copy);
        return ResponseEntity.ok().build();
    }

    // zmiana statusu książki na dowolny, np. gdy książka się w bibliotece zagubi lub zniszczy
    @PatchMapping("{copyId}/{status}")
    public ResponseEntity<Void> updateStatus(@PathVariable int copyId, @PathVariable String status) throws CopyNotFoundException {
        Copy copy = copyDbService.getCopy(copyId);
        copy.setStatus(status);
        Copy savedCopy = copyDbService.saveCopy(copy);
        copyMapper.mapToCopyDto(savedCopy);
        return ResponseEntity.ok().build();
    }

    // sprawdzenie ilości egzemplarzy ksiązki szukając po tytule, gdy nie znaleziono to rzucamy odpowiedni wyjątek
    @GetMapping("getQtyOf/{title}")
    public ResponseEntity<Integer> getQuantityOfCopies(@PathVariable String title) throws TitleNotFoundException2 {
        Optional<Title> result = Optional.ofNullable(titleDbService.getTitle(title));
        if (result.isPresent()) {
            int titleId = titleDbService.getTitle(title).getId();
            List<Copy> copies = copyDbService.getQuantityOfCopiesTo_Borrow(titleId);
            return ResponseEntity.ok(copies.size());
        } else {
            throw new TitleNotFoundException2();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<CopyDto> getCopy(@PathVariable final int id) throws CopyNotFoundException {
        return ResponseEntity.ok(copyMapper.mapToCopyDto(copyDbService.getCopy(id)));
    }
}