package com.busbooking.busapp.payload;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PaymentDTO {

    private Long id;

    @NotNull
    @Min(value=1, message="Payment amount should be greater than 1")
    @Max(value=10000, message="Payment amount should be equal or less than 10000")
    private BigDecimal amount;

    @NotNull
    @Min(value=1, message="Payment bookingId should be greater than 1")
    @Max(value=1000, message="Payment bookingId should be equal or less than 1000")
    private Long bookingId;

}
