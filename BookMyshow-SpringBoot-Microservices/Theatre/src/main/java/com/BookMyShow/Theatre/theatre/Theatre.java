package com.BookMyShow.Theatre.theatre;

import com.BookMyShow.Theatre.shows.Shows;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table(name = "theatre")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Theatre{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @OneToMany(mappedBy = "theatre",cascade = CascadeType.ALL)
    private List<Shows> shows = new ArrayList<>();
    @OneToMany(mappedBy = "theatre",cascade = CascadeType.ALL)
    private List<TheatreSeat> theatreSeat = new ArrayList<>();
    private String region;
}
