package com.BookMyShow.Movie;


import com.BookMyShow.Movie.Exceptions.MovieAlreadyExistsException;
import com.BookMyShow.Movie.Exceptions.MovieNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/book_my_show/movies")
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<String> addMovie(@RequestBody @Valid MovieRequest request) throws MovieAlreadyExistsException {
        return ResponseEntity.ok(movieService.addMovie(request));
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable("movieId") @Valid int movieId) throws MovieNotFoundException {
        System.out.println("Ikkadiki vachindi : " + movieId);
        MovieResponse movieById = movieService.getMovieById(movieId);
        System.out.println("movie response "+movieById);

        return ResponseEntity.ok(movieById);
    }


    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAllMovies(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @DeleteMapping("/{movie-name}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("movie-name") @Valid String name) throws MovieNotFoundException {
        movieService.deleteMovie(name);
        return ResponseEntity.accepted().build();
    }
}
