package com.BookMyShow.Movie;

import org.springframework.stereotype.Service;

@Service
public class MovieMapper {

    public MovieResponse fromMovie(Movie movie){
        return new MovieResponse(
                movie.getMovieName(),
                movie.getDuration(),
                movie.getRating(),
                movie.getReleaseDate(),
                movie.getLanguage()
        );

    }
    public Movie toMovie(MovieRequest request){
        return new Movie().builder()
                .movieName(request.movieName())
                .duration(request.duration())
                .rating(request.rating())
                .language(request.language())
                .build();
    }
}
