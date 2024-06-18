package com.BookMyShow.Theatre.shows;

import com.BookMyShow.Theatre.Exceptions.ShowNotAvailableException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/book_my_show/theatre/shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @PostMapping("/addShow")
    public ResponseEntity<String> addShow(@RequestBody @Valid ShowRequest request){
        try{
            String result = showService.addShow(request);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/fillTickets")
    public ResponseEntity<Void> fillShowTickets(@RequestBody @Valid Map<Integer, List<String>> map) throws ShowNotAvailableException {
        showService.fillShowTickets(map);
        return ResponseEntity.accepted().build();
    }

    @GetMapping("/showSeats/{show-id}")
    public ResponseEntity<List<ShowSeatResponse>> getShowSeats(@PathVariable("show-id") int showId) throws ShowNotAvailableException {
        showService.getShowSeats(showId);
        return ResponseEntity.accepted().build();
    }
}
