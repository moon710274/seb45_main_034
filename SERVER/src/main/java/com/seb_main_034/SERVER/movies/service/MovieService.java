package com.seb_main_034.SERVER.movies.service;

import com.seb_main_034.SERVER.movies.entity.Movie;
import com.seb_main_034.SERVER.movies.mapper.MovieMapper;
import com.seb_main_034.SERVER.movies.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
//    private final MovieMapper movieMapper;

    public Movie createMovie(Movie movie, long userId) {
        movie.setUser(userService.findUser(userId));
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Movie movie, long userId) {
        Movie findMovie = findMovie(movie.getMovieId());
        Long findMovieUserId = findMovie.getUser().getUserId();
        if (findMovieUserId.equals(userId)) {
            findMovie.setContent(movie.getContent());
            return movieRepository.save(findMovie);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "수정 권한이 없습니다.");
        }
    }

    public Movie findMovie(long movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "영화를 찾을 수 없습니다."));
    }

    public void deleteMovie(long movieId, long userId) {
        Movie findMovie = findMovie(movieId);
        Long findMovieUserId = findMovie.getUser().getUserId();
        if (findMovieUserId.equals(userId)) {
            movieRepository.delete(findMovie);
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "삭제 권한이 없습니다.");
        }
    }
}