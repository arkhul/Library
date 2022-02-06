package com.kodilla.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedNativeQuery(
        name = "Copy.getQuantityOfCopiesTo_Borrow",
        query = "SELECT * FROM COPIES WHERE STATUS = 'to_borrow' and TITLE_ID = :ID",
        resultClass = Copy.class
)
@Getter
@Entity
@Table (name = "COPIES")
@AllArgsConstructor
@NoArgsConstructor
public class Copy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @ManyToOne
    @JoinColumn (name = "TITLE_ID")
    private Title title;

    @NotNull
    @Column (name = "STATUS")
    private String status;

    public void setStatus(final String status) {
        this.status = status;
    }
}
