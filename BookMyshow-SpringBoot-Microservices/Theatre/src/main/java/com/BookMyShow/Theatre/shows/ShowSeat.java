package com.BookMyShow.Theatre.shows;

import com.BookMyShow.Theatre.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowSeat {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String seatNo;

    private double price;

    private boolean isAvailable;
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "showTable_id")
    @JsonIgnore
    private Shows shows;

}
