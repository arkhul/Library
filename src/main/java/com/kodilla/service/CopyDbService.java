package com.kodilla.service;

import com.kodilla.domain.Copy;
import com.kodilla.exception.CopyNotFoundException;
import com.kodilla.repository.CopyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CopyDbService {

    private final CopyRepository copyRepository;

    public List<Copy> getCopies() {
        return copyRepository.findAll();
    }

    public Copy saveCopy(final Copy copy) {
       return copyRepository.save(copy);
    }

    public Copy getCopy(final int copyId) throws CopyNotFoundException {
        return copyRepository.findById(copyId).orElseThrow(CopyNotFoundException::new);
    }

    public List<Copy> getQuantityOfCopiesTo_Borrow(final int id) {
        return copyRepository.getQuantityOfCopiesTo_Borrow(id);
    }
}
