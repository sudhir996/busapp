package com.busbooking.busapp.service.impl;

import com.busbooking.busapp.entities.Booking;
import com.busbooking.busapp.entities.Payment;
import com.busbooking.busapp.payload.PaymentDTO;
import com.busbooking.busapp.repository.BookingRepository;
import com.busbooking.busapp.repository.PaymentRepository;
import com.busbooking.busapp.service.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, BookingRepository bookingRepository) {
        this.paymentRepository = paymentRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public PaymentDTO createPayment(PaymentDTO paymentDTO) {
        Payment payment = new Payment();
        BeanUtils.copyProperties(paymentDTO, payment);

        Optional<Booking> optionalBooking = bookingRepository.findById(paymentDTO.getBookingId());
        if (optionalBooking.isPresent()) {
            Booking booking = optionalBooking.get();
            payment.setBooking(booking);
        }

        Payment createdPayment = paymentRepository.save(payment);
        PaymentDTO createdPaymentDTO = new PaymentDTO();
        BeanUtils.copyProperties(createdPayment, createdPaymentDTO);
        return createdPaymentDTO;
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        if (optionalPayment.isPresent()) {
            PaymentDTO paymentDTO = new PaymentDTO();
            BeanUtils.copyProperties(optionalPayment.get(), paymentDTO);
            return paymentDTO;
        }
        return null;
    }

}
