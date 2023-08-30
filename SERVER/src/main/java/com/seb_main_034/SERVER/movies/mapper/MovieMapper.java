package com.seb_main_034.SERVER.movies.mapper;

import com.seb_main_034.SERVER.movies.dto.MoviePatchDto;
import com.seb_main_034.SERVER.movies.dto.MoviePostDto;
import com.seb_main_034.SERVER.movies.dto.MovieResponseDto;
import com.seb_main_034.SERVER.movies.entity.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    Movie moviePostDtoToMovie(MoviePostDto moviePostDto);
    Movie moviePatchDtoToMovie(MoviePatchDto moviePatchDto);
    MovieResponseDto movieToMovieResponseDto(Movie movie);
}
