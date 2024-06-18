package com.BookMyShow.Ticket;

import com.BookMyShow.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table
@Builder
public class Ticket {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int showId;
    private int userId;
    private List<String> showSeats ;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime lastModifiedDate;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

}
