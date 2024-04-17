package com.example.UberReviewService.services;

import com.example.UberReviewService.models.Booking;
import com.example.UberReviewService.models.Driver;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.BookingRepository;
import com.example.UberReviewService.repositories.DriverRepository;
import com.example.UberReviewService.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ReviewService implements CommandLineRunner {

    private ReviewRepository reviewRepository;
    private BookingRepository bookingRepository;
    private DriverRepository driverRepository;

    public ReviewService(ReviewRepository reviewRepository, BookingRepository bookingRepository, DriverRepository driverRepository) {
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("***********************");

//        Review r = Review.builder()
//                .content("Amazin ride quality")
//                .rating(4.0)
//                .build(); // code to create plain java object
//
//        Booking b = Booking
//                .builder()
//                .review(r)
//                .endTime(new Date())
//                .build();
//
//        bookingRepository.save(b);
//
//        System.out.println(r);
////        reviewRepository.save(r); // this code executes sql query // this is not required when we use cascade
//        System.out.println(r.getId());
//
//        List<Review> reviewList = reviewRepository.findAll();
//
//        for(Review review : reviewList) {
//            System.out.println(review.getContent());
//        }
//
//        reviewRepository.deleteById(2L);


        List<Long> driverIds = new ArrayList<>(Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L));
        List<Driver> drivers = driverRepository.findAllByIdIn(driverIds);

//        List<Booking> bookings = bookingRepository.findAllByDriverIn(drivers);

        for(Driver driver : drivers) {
            List<Booking> bookings = driver.getBookings();
            bookings.forEach(booking -> System.out.println(booking.getId()));
        }
    }
}
