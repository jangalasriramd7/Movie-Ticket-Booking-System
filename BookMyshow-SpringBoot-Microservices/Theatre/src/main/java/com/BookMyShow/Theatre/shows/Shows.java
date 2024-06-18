package com.BookMyShow.Theatre.shows;

import com.BookMyShow.Theatre.enums.ShowStatus;
import com.BookMyShow.Theatre.theatre.Theatre;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Entity
@Table
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Shows{
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonFormat(pattern="HH:mm:ss")
    private LocalTime showTime;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate showDate;
    @Enumerated(EnumType.STRING)
    private ShowStatus showStatus;
    private int movieId;
    @ManyToOne
    @JoinColumn(name = "theatre_id")
    @JsonIgnore
    private Theatre theatre;
    @OneToMany(mappedBy = "shows",cascade = CascadeType.ALL)
    private List<ShowSeat> showSeatList = new ArrayList<>();

}
