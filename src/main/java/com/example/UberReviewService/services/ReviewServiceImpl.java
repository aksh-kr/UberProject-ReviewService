package com.example.UberReviewService.services;

import com.example.UberReviewService.models.Review;
import com.example.UberReviewService.repositories.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{

    private ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Optional<Review> findReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public boolean deleteReviewById(Long id) {
        try {
            reviewRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Review publishReview(Review review) {
        return this.reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Long id, Review newReview) {
        try{
             Review review = this.reviewRepository.findById(id).get();
             if(newReview.getId() != null) {
                 review.setId(newReview.getId());
             }
             if(newReview.getContent() != null) {
                 review.setContent(newReview.getContent());
             }
        } catch (Exception e) {
            throw new EntityNotFoundException(e.getMessage());
        }
        return this.reviewRepository.save(newReview);
    }
}
