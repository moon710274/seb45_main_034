package com.seb_main_034.SERVER.movies.repository;

import com.seb_main_034.SERVER.movies.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
