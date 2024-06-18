package com.BookMyShow.Movie;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "movies")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "movieName", nullable = false)
    private String movieName;
    private double duration;
    private double rating;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate releaseDate;
    @Enumerated(EnumType.STRING)
    private Language language;
}
