package com.kodilla.controller;

import com.kodilla.domain.Borrow;
import com.kodilla.domain.Copy;
import com.kodilla.dto.BorrowDto;
import com.kodilla.exception.BorrowNotFoundException;
import com.kodilla.exception.CopyNotFoundException;
import com.kodilla.exception.IllegalOperation;
import com.kodilla.mapper.BorrowMapper;
import com.kodilla.service.BorrowDbService;
import com.kodilla.service.CopyDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/borrows")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowDbService borrowDbService;
    private final BorrowMapper borrowMapper;
    private final CopyDbService copyDbService;

    @GetMapping
    public ResponseEntity<List<BorrowDto>> getBorrows() {
        List<Borrow> borrows = borrowDbService.getBorrows();
        return ResponseEntity.ok(borrowMapper.mapToBorrowDtoList(borrows));
    }

    // wystarczy podać id_readera i id_copii, daty tworzą się automatycznie
    // (borrow_date = LocalDate.now), (return_date = null)
    // status wypożyczanej copii zmienia się na "in_circulation"
    // jeżeli copy_id ma status inny niż "to_borrow", rzucony zostaje wyjątek, metoda jest przerwana
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createBorrow(@RequestBody BorrowDto borrowDto) throws IllegalOperation, CopyNotFoundException {
        Borrow borrow = borrowMapper.mapToBorrow(borrowDto);
        int copyId = borrow.getCopy().getId();
        Copy copy = copyDbService.getCopy(copyId);
        if ("to_borrow".equals(copy.getStatus())) {
            copy.setStatus("in_circulation");
            borrowDbService.saveBorrow(borrow);
            copyDbService.saveCopy(copy);
            return ResponseEntity.ok().build();
        } else {
            throw new IllegalOperation();
        }
    }

    // zwrot książki: zmiana statusu w podanym borrow_id na wybrany przez użytkownika
    // return_date zmienia się automatycznie na LocalDate.now
    @PatchMapping("{borrowId}/{status}")
    public ResponseEntity<Void> returnCopy(@PathVariable int borrowId, @PathVariable String status) throws BorrowNotFoundException, CopyNotFoundException {
        Borrow borrow = borrowDbService.getBorrow(borrowId);
        borrow.setReturnDate();
        Borrow savedBorrow = borrowDbService.saveBorrow(borrow);
        int copyId = borrow.getCopy().getId();
        Copy copy = copyDbService.getCopy(copyId);
        copy.setStatus(status);
        copyDbService.saveCopy(copy);
        borrowMapper.mapToBorrowDto(savedBorrow);
        return ResponseEntity.ok().build();
    }
}
