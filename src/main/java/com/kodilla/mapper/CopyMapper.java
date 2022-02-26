package com.kodilla.mapper;

import com.kodilla.domain.Copy;
import com.kodilla.dto.CopyDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CopyMapper {

    public CopyDto mapToCopyDto(final Copy copy) {
        return new CopyDto(
                copy.getId(),
                copy.getTitle().getTitle(),
                copy.getStatus()
        );
    }

    public List<CopyDto> mapToCopyDtoList(final List<Copy> copyList) {
        return copyList.stream()
                .map(this::mapToCopyDto)
                .collect(Collectors.toList());
    }
}
