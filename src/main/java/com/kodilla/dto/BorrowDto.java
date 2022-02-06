package com.kodilla.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.domain.Copy;
import com.kodilla.domain.Reader;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BorrowDto {

    @JsonProperty("ID")
    private int id;
    @JsonProperty("READER")
    private Reader reader;
    @JsonProperty("COPY")
    private Copy copy;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowDto(final Reader reader, final Copy copy) {
        this.reader = reader;
        this.copy = copy;
        this.borrowDate = LocalDate.now();
        this.returnDate = null;
    }
}
