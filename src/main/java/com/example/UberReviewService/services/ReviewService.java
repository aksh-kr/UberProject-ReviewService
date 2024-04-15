package com.example.UberReviewService.services;

import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReviewService implements CommandLineRunner {

    private ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("***********************");
        Review r = Review.builder()
                .content("Amazin ride quality")
                .rating(4.0)
                .build(); // code to create plain java object
        System.out.println(r);
        reviewRepository.save(r); // this code executes sql query
        System.out.println(r.getId());

        List<Review> reviewList = reviewRepository.findAll();

        for(Review review : reviewList) {
            System.out.println(review.getContent());
        }

        reviewRepository.deleteById(2L);
    }
}
