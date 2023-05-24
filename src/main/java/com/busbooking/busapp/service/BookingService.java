package com.busbooking.busapp.service;

import com.busbooking.busapp.payload.BookingDTO;
import java.util.List;

public interface BookingService {

    BookingDTO createBooking(BookingDTO bookingDTO);

    BookingDTO getBookingById(Long id);

    List<BookingDTO> getAllBookings();

    BookingDTO updateBooking(Long id, BookingDTO updatedBookingDTO);

    void deleteBooking(Long id);

}
