package com.BookMyShow.Theatre.theatre;

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
    public ResponseEntity<String> addTheatre(@RequestBody TheatreRequest request){
        try{
            String result = theatreservice.addTheatre(request);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/region/{region}")
    public ResponseEntity<List<Theatre>> getAllTheatresFromRegion(@PathVariable("region")String regionName){
        try{
            List<Theatre> result = theatreservice.getAllTheatresFromRegion(regionName);
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{theatreId}")
    public ResponseEntity<List<Shows>> getAllShowsOfTheatre(@PathVariable("theatreId") int theatreId){
        try{
            List<Shows> result = theatreservice.getAllShowsOfTheatre(theatreId);
            return new ResponseEntity<>(result, HttpStatus.FOUND);
        }
        catch(Exception e){
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
        }
    }

//    @GetMapping("/movie/{name}")
//    public ResponseEntity<List<Theatre>> getAllTheatresByMovieName(@PathVariable("name") String movieName){
//        try{
//            List<Theatre> result = theatreservice.getAllTheatresByMovieName(movieName);
//            return new ResponseEntity<>(result, HttpStatus.FOUND);
//        }
//        catch(Exception e){
//            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NOT_FOUND);
//        }
//    }
}
