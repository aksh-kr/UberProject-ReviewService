package com.example.UberReviewService.controllers;

import com.example.UberReviewService.adapters.CreateReviewDtoToReviewAdapter;
import com.example.UberReviewService.dtos.CreateReviewDto;
import com.example.UberReviewService.dtos.ReviewDto;
import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private ReviewService reviewService;
    private CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter;

    public ReviewController(ReviewService reviewService, CreateReviewDtoToReviewAdapter createReviewDtoToReviewAdapter) {
        this.reviewService = reviewService;
        this.createReviewDtoToReviewAdapter = createReviewDtoToReviewAdapter;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = this.reviewService.findAllReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<?> findReviewById(@PathVariable("reviewId") Long reviewId) {
        try {
            Optional<Review> review = this.reviewService.findReviewById(reviewId);
            return new ResponseEntity<>(review.get(), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> addReview(@RequestBody CreateReviewDto reviewRequest) {
        Review incomingReview = this.createReviewDtoToReviewAdapter.convertDto(reviewRequest);
        if(incomingReview == null) {
            return new ResponseEntity<>("Invalid arguments", HttpStatus.BAD_REQUEST);
        }
        Review createdReview = this.reviewService.publishReview(incomingReview);
        ReviewDto response = ReviewDto.builder()
                .id(createdReview.getId())
                .rating(createdReview.getRating())
                .content(createdReview.getContent())
                .booking(createdReview.getBooking().getId())
                .createdAt(createdReview.getCreatedAt())
                .updatedAt(createdReview.getUpdatedAt())
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReviewById(@PathVariable("reviewId") Long reviewId) {
        try {
            boolean isDeleted =  this.reviewService.deleteReviewById(reviewId);
            if (!isDeleted) {
               return new ResponseEntity<>("Unable to delete review", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return ResponseEntity.ok("Review deleted for given reviewId") ;
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
