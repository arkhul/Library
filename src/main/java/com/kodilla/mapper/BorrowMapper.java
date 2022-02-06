package com.kodilla.mapper;

import com.kodilla.domain.Borrow;
import com.kodilla.dto.BorrowDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BorrowMapper {

    public Borrow mapToBorrow(BorrowDto borrowDto) {
        return new Borrow(
                borrowDto.getReader(),
                borrowDto.getCopy()
        );
    }

    public BorrowDto mapToBorrowDto(Borrow borrow) {
        return new BorrowDto(
                borrow.getReader(),
                borrow.getCopy()
        );
    }

    public List<BorrowDto> mapToBorrowDtoList(List<Borrow> borrowList) {
        return borrowList.stream()
                .map(this::mapToBorrowDto)
                .collect(Collectors.toList());
    }
}
