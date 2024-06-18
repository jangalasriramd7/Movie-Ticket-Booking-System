package com.BookMyShow.Theatre.theatre;

import com.BookMyShow.Theatre.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "theatre_seats")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TheatreSeat {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String seatNo;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="theatre_id", referencedColumnName="id")
    @JsonIgnore
    private Theatre theatre;
}
