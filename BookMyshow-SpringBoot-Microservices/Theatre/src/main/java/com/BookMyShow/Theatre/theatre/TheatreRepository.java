package com.BookMyShow.Theatre.theatre;


import com.BookMyShow.Theatre.shows.Shows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {

    /**
     * @param theatre must not be {@literal null}.
     * @return theatre
     */
    Theatre save(Theatre theatre);

    List<Theatre> findByRegion(String regionName);

    List<Shows> findByName(@Param("theatreName") String theatreName);


    List<Theatre> findAll();
    Optional<Theatre> findById(int id);
    Optional<Theatre> findByNameAndRegion(String name, String region);
}
