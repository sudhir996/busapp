package com.busbooking.busapp.service;

import com.busbooking.busapp.payload.PaymentDTO;

public interface PaymentService {
    PaymentDTO createPayment(PaymentDTO paymentDTO);

    PaymentDTO getPaymentById(Long id);

}
