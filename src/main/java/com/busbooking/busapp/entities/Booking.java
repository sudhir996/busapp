package com.busbooking.busapp.entities;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookings",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})}
      )
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="customerId" , nullable = false)
    private Long customerId;

    @Column(name="busId" , nullable = false)
    private Long busId;

    @Column(name="bookingDate" , nullable = false)
    private LocalDateTime bookingDate;

    @Column(name="source" , nullable = false)
    private String source;

    @Column(name="destination" , nullable = false)
    private String destination;

    @OneToMany(mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<Payment> payments = new HashSet<>();

}
