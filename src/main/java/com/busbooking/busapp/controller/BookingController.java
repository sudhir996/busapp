package com.busbooking.busapp.controller;

import com.busbooking.busapp.entities.Booking;
import com.busbooking.busapp.payload.BookingDTO;
import com.busbooking.busapp.repository.BookingRepository;
import com.busbooking.busapp.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<BookingDTO> createBooking(@Valid @RequestBody BookingDTO bookingDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Handle validation errors
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        BookingDTO createdBooking = bookingService.createBooking(bookingDTO);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdBooking.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdBooking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> getBookingById(@PathVariable("id") Long id) {
        BookingDTO bookingDTO = bookingService.getBookingById(id);
        if (bookingDTO != null) {
            return ResponseEntity.ok(bookingDTO);
        }
        return ResponseEntity.notFound().build();
    }

//    @GetMapping
//    public ResponseEntity<List<Booking>> getAllBookings() {
//        List<Booking> bookings = bookingService.findAll();
//        return new ResponseEntity<>(bookings, HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Booking> updateBooking(@PathVariable Long id, @RequestBody Booking updatedBooking) {
//        Optional<Booking> optionalBooking = bookingService.findById(id);
//        return optionalBooking.map(booking -> {
//            booking.setCustomerId(updatedBooking.getCustomerId());
//            booking.setBusId(updatedBooking.getBusId());
//            booking.setBookingDate(updatedBooking.getBookingDate());
//            booking.setSource(updatedBooking.getSource());
//            booking.setDestination(updatedBooking.getDestination());
//            Booking savedBooking = bookingService.save(booking);
//            return new ResponseEntity<>(savedBooking, HttpStatus.OK);
//        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
//        Optional<Booking> optionalBooking = bookingService.findById(id);
//        if (optionalBooking.isPresent()) {
//            bookingService.delete(optionalBooking.get());
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
