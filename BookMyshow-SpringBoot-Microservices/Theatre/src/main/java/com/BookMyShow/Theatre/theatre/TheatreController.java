package com.BookMyShow.Theatre.theatre;

import com.BookMyShow.Theatre.Exceptions.TheatreAlreadyExistsException;
import com.BookMyShow.Theatre.Exceptions.TheatreNotFoundException;
import com.BookMyShow.Theatre.shows.ShowResponse;
import com.BookMyShow.Theatre.shows.Shows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/book_my_show/theatres")
public class TheatreController {
    @Autowired
    TheatreService theatreservice;

    @PostMapping("/add")
    public ResponseEntity<String> addTheatre(@RequestBody TheatreRequest request) throws TheatreAlreadyExistsException {
        String result = theatreservice.addTheatre(request);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("/region/{region}")
    public ResponseEntity<List<TheatreResponse>> getAllTheatresFromRegion(@PathVariable("region")String regionName){
        List<TheatreResponse> result = theatreservice.getAllTheatresFromRegion(regionName);
        return new ResponseEntity<>(result, HttpStatus.FOUND);
    }

    @GetMapping("/{theatreId}")
    public ResponseEntity<TheatreResponse> findTheatreById(@PathVariable("theatreId") int theatreId) throws TheatreNotFoundException {
        TheatreResponse result = theatreservice.findTheatreById(theatreId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
