package com.example.UberReviewService.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends BaseModel{

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Review review; // we have defined a 1:1 relationship between booking and review

    @Enumerated(value = EnumType.STRING)
    private BookingStatus bookingStatus;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startTime;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date endTime;

    private Long totalDistance;

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @ManyToOne
    private Passenger passenger;
}
