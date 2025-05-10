package org.example.api.Controller;


import org.example.api.Entities.dtos.ReviewDTO;
import org.example.api.Service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/review")
public class ReviewController {


    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/usuario/{id}/ultimas")
    public ResponseEntity<List<ReviewDTO>> obtenerUltimasTresReviews(@PathVariable Long id) {
        List<ReviewDTO> reviews = reviewService.obtenerUltimasTresReviews(id);
        return ResponseEntity.ok(reviews);
    }

}
