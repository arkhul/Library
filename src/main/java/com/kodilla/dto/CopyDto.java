package com.kodilla.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.domain.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CopyDto {

    @JsonProperty("ID")
    private int id;
    @JsonProperty("TITLE")
    private Title title;
    @JsonProperty("STATUS")
    private String status;
}

