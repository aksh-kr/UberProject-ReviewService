package com.example.UberReviewService.services;

import com.example.UberReviewService.models.Booking;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.BookingRepository;
import com.example.UberReviewService.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.Date;
import java.util.List;

@Service
public class ReviewService implements CommandLineRunner {

    private ReviewRepository reviewRepository;
    private BookingRepository bookingRepository;

    public ReviewService(ReviewRepository reviewRepository, BookingRepository bookingRepository) {
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("***********************");

        Review r = Review.builder()
                .content("Amazin ride quality")
                .rating(4.0)
                .build(); // code to create plain java object

        Booking b = Booking
                .builder()
                .review(r)
                .endTime(new Date())
                .build();

        bookingRepository.save(b);

        System.out.println(r);
//        reviewRepository.save(r); // this code executes sql query
        System.out.println(r.getId());

        List<Review> reviewList = reviewRepository.findAll();

        for(Review review : reviewList) {
            System.out.println(review.getContent());
        }

        reviewRepository.deleteById(2L);
    }
}
