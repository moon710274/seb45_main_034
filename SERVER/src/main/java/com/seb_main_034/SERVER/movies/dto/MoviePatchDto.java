package com.seb_main_034.SERVER.movies.dto;

import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Text;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class MoviePatchDto {

    private long MovieId;

    @NotNull
    private String title;

    @NotNull
    private Text content;
}
