package com.kodilla.mapper;

import com.kodilla.domain.Borrow;
import com.kodilla.dto.BorrowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowMapper {

    public BorrowDto mapToBorrowDto(final Borrow borrow) {
        return new BorrowDto(
                borrow.getId(),
                borrow.getReader().getName(),
                borrow.getCopy().getTitle().getTitle(),
                borrow.getBorrowDate(),
                borrow.getReturnDate()
        );
    }

    public List<BorrowDto> mapToBorrowDtoList(List<Borrow> borrowList) {
        return borrowList.stream()
                .map(this::mapToBorrowDto)
                .collect(Collectors.toList());
    }
}
