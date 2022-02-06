package com.kodilla.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReaderDto {

    private int id;
    private String name;
    private LocalDate accountCreationDate;

    public ReaderDto(final int id, final String name) {
        this.id = id;
        this.name = name;
        this.accountCreationDate = LocalDate.now();
    }
}
