package com.BookMyShow.Movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByMovieName(String name);

    @Query(value = "select * from movies m where m.language =:language", nativeQuery = true)
    List<Movie> findMovieByLanguage(String language);
}
