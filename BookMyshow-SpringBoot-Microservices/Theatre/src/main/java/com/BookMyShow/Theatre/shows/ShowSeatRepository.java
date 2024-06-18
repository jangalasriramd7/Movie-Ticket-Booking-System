package com.BookMyShow.Theatre.shows;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

}
