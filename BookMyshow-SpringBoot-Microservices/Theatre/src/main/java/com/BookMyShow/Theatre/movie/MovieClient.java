package com.BookMyShow.Theatre.movie;

import com.BookMyShow.Theatre.Exceptions.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.springframework.http.HttpMethod.GET;

@FeignClient(
        value = "movie-service",
        url = "${application.config.movie-url}"
)
public interface MovieClient {
    @GetMapping("/{movieId}")
    ResponseEntity<MovieResponse> getMovieById(@PathVariable("movieId") int movieId);
}
