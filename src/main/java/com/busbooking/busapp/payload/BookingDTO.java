package com.busbooking.busapp.payload;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
public class BookingDTO {

    private Long id;

    @NotNull
    @Min(value=1, message="Booking customerId should be greater than 1")
    @Max(value=1000, message="Booking customerId should be equal or less than 1000")
    private Long customerId;

    @NotNull
    @Min(value=1, message="Booking busId should be greater than 1")
    @Max(value=1000, message="Booking busId should be equal or less than 1000")
    private Long busId;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime bookingDate;

    @NotNull
    @NotEmpty
    @Size(min = 3, message = "Booking source should have at least 3 characters")
    private String source;

    @NotNull
    @NotEmpty
    @Size(min = 3, message = "Booking destination should have at least 3 characters")
    private String destination;

}
