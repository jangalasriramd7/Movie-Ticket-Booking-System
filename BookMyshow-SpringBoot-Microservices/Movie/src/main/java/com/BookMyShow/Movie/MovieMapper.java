package com.BookMyShow.Movie;

import org.springframework.stereotype.Service;

@Service
public class MovieMapper {

    public MovieResponse fromMovie(Movie movie){
        return new MovieResponse(
                movie.getId(),
                movie.getMovieName(),
                movie.getDuration(),
                movie.getRating(),
                movie.getReleaseDate(),
                movie.getLanguage()
        );

    }
    public Movie toMovie(MovieRequest request){
        return Movie.builder()
                .id(request.id())
                .movieName(request.movieName())
                .duration(request.duration())
                .rating(request.rating())
                .language(request.language())
                .build();
    }
}
