package com.BookMyShow.Theatre.shows;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowsRepository extends JpaRepository<Shows, Integer> {
//    @Query("SELECT s FROM shows s JOIN theatre t ON s.theatre.theatre_id = s.id WHERE t.name = :theatreName")
    List<Shows> findByTheatreId(int theatreId);
}
