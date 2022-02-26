package com.kodilla.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowDto {

    private int id;
    private String reader;
    private String copy;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}

