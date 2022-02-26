package com.kodilla.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "READERS")
@AllArgsConstructor
@NoArgsConstructor
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "ACCOUNT_CREATION_DATE")
    private LocalDate accountCreationDate;

    @OneToMany(
            targetEntity = Borrow.class,
            mappedBy = "reader",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Borrow> borrowList = new ArrayList<>();

    public Reader(final int id, final String name) {
        this.id = id;
        this.name = name;
        this.accountCreationDate = LocalDate.now();
    }
}
