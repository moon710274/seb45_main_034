package com.seb_main_034.SERVER.movies.controller;

import com.seb_main_034.SERVER.movies.dto.MoviePatchDto;
import com.seb_main_034.SERVER.movies.dto.MoviePostDto;
import com.seb_main_034.SERVER.movies.dto.MovieResponseDto;
import com.seb_main_034.SERVER.movies.entity.Movie;
import com.seb_main_034.SERVER.movies.mapper.MovieMapper;
import com.seb_main_034.SERVER.movies.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

import static com.sun.tools.doclint.Entity.or;

@RestController
@RequestMapping("/movies")
@Validated
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @PostMapping
    public ResponseEntity postMovie(@Valid @RequestBody MoviePostDto moviePostDto) {
        Movie movie = movieService.createMovie(movieMapper.moviePostDtoToMovie(moviePostDto));
        MovieResponseDto response = movieMapper.movieToMovieResponseDto(movie);
        return new ResponseEntity<>(response, HttpStatus.CREATED)
    }

    @PatchMapping("/{movie-id}")
    public ResponseEntity patchMovie(@PathVariable("movie-id") @Positive long movieId,
                                     @Valid @RequestBody MoviePatchDto moviePatchDto) {
        moviePatchDto.setMovieId(movieId);
        Movie movie = movieService.updateMovie(movieMapper.moviePatchDtoToMovie(moviePatchDto));
        MovieResponseDto response = movieMapper.movieToMovieResponseDto(movie);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getMovies(@Positive @RequestParam int page,
                                    @Positive @RequestParam int size) {
        Page<Movie> Orders = movieService.findMovie(page -1, size);
        List<Movie> movies = Orders.getContent();
        List<MovieResponseDto> response = movieMapper.movieToMovieResponseDto(movies);

        return new ResponseEntity<>(response, HttpStatus.OK)
    }

    @DeleteMapping("/{movie-id}")
    public ResponseEntity deleteMovie(@PathVariable("movie-id") @Positive long movieId) {
        movieService.deleteMovie(movieId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
