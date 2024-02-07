package book.com.library.controller;

import book.com.library.dto.ReviewDTO;
import book.com.library.model.Review;
import book.com.library.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAllReviews() {
        try {
            List<Review> reviews = reviewService.getAllReviews();
            List<ReviewDTO> reviewDTOS = reviews.stream().map(review -> new ReviewDTO(review.getId(), review.getRating())).collect(Collectors.toList());
            return new ResponseEntity<>(reviewDTOS, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
