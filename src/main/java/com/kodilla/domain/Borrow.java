package com.kodilla.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Entity(name = "BORROWS")
@NoArgsConstructor
@AllArgsConstructor
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "ID")
    private int id;

    @ManyToOne
    @JoinColumn (name = "READER_ID")
    private Reader reader;

    @ManyToOne
    @JoinColumn (name = "COPY_ID")
    private Copy copy;

    @NotNull
    @Column (name = "BORROW_DATE")
    private LocalDate borrowDate;

    @Column (name = "RETURN_DATE")
    private LocalDate returnDate;

    public Borrow(final Reader reader, final Copy copy) {
        this.reader = reader;
        this.copy = copy;
        this.borrowDate = LocalDate.now();
        this.returnDate = null;
    }

    public void setReturnDate() {
        this.returnDate = LocalDate.now();
    }
}
