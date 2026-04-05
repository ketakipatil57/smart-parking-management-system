package com.pict.Controller;

import com.pict.Entity.Booking;
import com.pict.Service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Booking")
public class BookingController {


    private BookingService bookingService;

    public BookingController(BookingService bookingService){
        this.bookingService = bookingService;
    }


    @PostMapping("/bookslot/{slotNumber}")
    public ResponseEntity<Booking> bookSlot(
            @PathVariable String slotNumber,
            @RequestBody Booking booking) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookingService.bookSlot(slotNumber, booking));
    }

    @GetMapping("/allBooking")
    public ResponseEntity<List<Booking>> getAllBookings(){
        return ResponseEntity.ok( bookingService.getAllBookings());
    }


    @PutMapping("/checkout/{id}")
    public ResponseEntity<Booking> checkout(@PathVariable Long id){
        return ResponseEntity.ok(bookingService.checkout(id));
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id){
        bookingService.cancelBooking(id);
        return ResponseEntity.ok("Booking cancelled successfully");
    }
}
