package com.kodilla.service;

import com.kodilla.domain.Borrow;
import com.kodilla.exception.BorrowNotFoundException;
import com.kodilla.repository.BorrowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowDbService {

    private final BorrowRepository borrowRepository;

    public List<Borrow> getBorrows() {
        return borrowRepository.findAll();
    }

    public Borrow saveBorrow(final Borrow borrow) {
        return borrowRepository.save(borrow);
    }

    public Borrow getBorrow(final int borrowId) throws BorrowNotFoundException {
        return borrowRepository.findById(borrowId).orElseThrow(BorrowNotFoundException::new);
    }
}
