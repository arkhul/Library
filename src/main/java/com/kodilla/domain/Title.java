package com.kodilla.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table (name = "TITLES")
@AllArgsConstructor
@NoArgsConstructor
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @NotNull
    @Column (name = "TITLE")
    private String title;

    @NotNull
    @Column (name = "AUTHOR")
    private String author;

    @NotNull
    @Column (name = "PUBLICATION_YEAR")
    private int publicationYear;

    @OneToMany (
            targetEntity = Copy.class,
            mappedBy = "title",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private final List<Copy> copyList = new ArrayList<>();
}
