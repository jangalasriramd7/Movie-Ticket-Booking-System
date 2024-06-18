package com.BookMyShow.Movie;

import com.BookMyShow.Movie.Exceptions.MovieAlreadyExistsException;
import com.BookMyShow.Movie.Exceptions.MovieNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService{

    private final MovieRepository movieRepository;
    private final MovieMapper mapper;

    public String addMovie(MovieRequest request) throws MovieAlreadyExistsException {
        var movie = movieRepository.findByMovieName(request.movieName());
        if(movie.isEmpty()){
            movieRepository.save(mapper.toMovie(request));
        }
        else{
            throw new MovieAlreadyExistsException(request.movieName() + " already exists in the Movie DB");
        }
        return "Movie added successfully";
    }

    public MovieResponse getMovieById(int id) throws MovieNotFoundException {
        System.out.println("Vachinda raleda>.... Vaste entha pandem " + id);
        return movieRepository.findById((long)id)
                .map(mapper::fromMovie)
                .orElseThrow(() -> new MovieNotFoundException(
                        String.format("Invalid Movie Name: No movie present with the given name:: %s", id)
                ));
    }


    public List<MovieResponse> getMoviesByLanguage(String language) {
        return movieRepository.findMovieByLanguage(language)
                .stream()
                .map(mapper::fromMovie)
                .collect(Collectors.toList());
    }

    public List<MovieResponse> getAllMovies(){
        return movieRepository.findAll()
                .stream()
                .map(mapper::fromMovie)
                .collect(Collectors.toList());
    }

    public void deleteMovie(String name) throws MovieNotFoundException {
        Movie response = movieRepository.findByMovieName(name)
                .orElseThrow(() -> new MovieNotFoundException("Movie Not present with the given name"));
        movieRepository.deleteById((long)response.getId());
    }
}
