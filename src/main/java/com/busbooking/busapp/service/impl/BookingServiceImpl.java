package com.busbooking.busapp.service.impl;

import com.busbooking.busapp.entities.Booking;
import com.busbooking.busapp.payload.BookingDTO;
import com.busbooking.busapp.repository.BookingRepository;
import com.busbooking.busapp.service.BookingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public BookingDTO createBooking(BookingDTO bookingDTO) {
        Booking booking = new Booking();
        BeanUtils.copyProperties(bookingDTO, booking);
        Booking createdBooking = bookingRepository.save(booking);
        BookingDTO createdBookingDTO = new BookingDTO();
        BeanUtils.copyProperties(createdBooking, createdBookingDTO);
        return createdBookingDTO;
    }

    @Override
    public BookingDTO getBookingById(Long id) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            BookingDTO bookingDTO = new BookingDTO();
            BeanUtils.copyProperties(optionalBooking.get(), bookingDTO);
            return bookingDTO;
        }
        return null;
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(booking -> {
                    BookingDTO bookingDTO = new BookingDTO();
                    BeanUtils.copyProperties(booking, bookingDTO);
                    return bookingDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public BookingDTO updateBooking(Long id, BookingDTO updatedBookingDTO) {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            BeanUtils.copyProperties(updatedBookingDTO, booking);
            Booking savedBooking = bookingRepository.save(booking);
            BookingDTO savedBookingDTO = new BookingDTO();
            BeanUtils.copyProperties(savedBooking, savedBookingDTO);
            return savedBookingDTO;
        }
        return null;
    }

    @Override
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }
}
