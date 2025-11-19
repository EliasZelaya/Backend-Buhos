package com.example.eeza.backendbuhoseat.services.impl;

import com.example.eeza.backendbuhoseat.domain.dto.request.review.CreateReviewRequest;
import com.example.eeza.backendbuhoseat.domain.dto.request.review.UpdateReviewRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.review.ReviewResponse;
import com.example.eeza.backendbuhoseat.domain.dto.response.user.UserResponse;
import com.example.eeza.backendbuhoseat.domain.entities.Local;
import com.example.eeza.backendbuhoseat.domain.entities.Review;
import com.example.eeza.backendbuhoseat.exception.ReviewNotFoundException;
import com.example.eeza.backendbuhoseat.exception.UserAlreadyHaveReviewException;
import com.example.eeza.backendbuhoseat.repository.LocalRepository;
import com.example.eeza.backendbuhoseat.repository.ReviewRepository;
import com.example.eeza.backendbuhoseat.services.ReviewService;
import com.example.eeza.backendbuhoseat.services.UserService;
import com.example.eeza.backendbuhoseat.utils.mappers.ReviewMapper;
import com.example.eeza.backendbuhoseat.utils.mappers.UserMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.eeza.backendbuhoseat.utils.Constants.*;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final LocalRepository localRepository;

    @Override
    public void createReview(CreateReviewRequest createReviewRequest) {
        UserResponse user = userService.getUserById(createReviewRequest.getUserId());
        Local local = localRepository.findLocalById(createReviewRequest.getLocalId());
        boolean haveReview = reviewRepository.findByUser_IdAndLocal_Id(
                createReviewRequest.getUserId(), createReviewRequest.getLocalId()
        ).isEmpty();

        if (!haveReview)
            throw new UserAlreadyHaveReviewException(ENTITY_USER+HAVE_REVIEW);

        ReviewMapper.toDto(
                reviewRepository.save(ReviewMapper.toEntityCreate(
                        createReviewRequest, UserMapper.toEntity(user), local
                ))
        );
    }

    @Override
    public List<ReviewResponse> findByLocalId(UUID localId) {
        List<ReviewResponse> reviews = ReviewMapper.toDtoList(reviewRepository.findByLocalId(localId));
        if(reviews.isEmpty())
            throw new ReviewNotFoundException(ENTITY_REVIEW+NOT_FOUND);

        return reviews;
    }

    @Override
    @Transactional
    public void updateReview(UpdateReviewRequest updateReviewRequest) {
        Review review = reviewRepository.findById(updateReviewRequest.getReviewId())
                .orElseThrow(()  -> new ReviewNotFoundException(ENTITY_REVIEW+NOT_FOUND));
        if(!updateReviewRequest.getContent().isEmpty()) {
            review.setContent(updateReviewRequest.getContent());
        }
        if(updateReviewRequest.getStars() != null) {
            review.setStars(updateReviewRequest.getStars());
        }
        reviewRepository.save(review);
    }

    @Override
    @Transactional
    public void deleteReview(UUID id) {
        reviewRepository.findById(id)
                .orElseThrow(()  -> new ReviewNotFoundException(ENTITY_REVIEW+NOT_FOUND));

        reviewRepository.deleteById(id);
    }
}
