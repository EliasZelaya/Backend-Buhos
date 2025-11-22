package com.example.eeza.backendbuhoseat.services.impl;

import com.example.eeza.backendbuhoseat.domain.dto.request.subreview.CreateSubReviewRequest;
import com.example.eeza.backendbuhoseat.domain.dto.request.subreview.UpdateSubReviewRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.local.LocalResponse;
import com.example.eeza.backendbuhoseat.domain.dto.response.review.ReviewResponse;
import com.example.eeza.backendbuhoseat.domain.dto.response.subreview.SubReviewResponse;
import com.example.eeza.backendbuhoseat.domain.dto.response.user.UserResponse;
import com.example.eeza.backendbuhoseat.exception.SubReviewAlreadyExistException;
import com.example.eeza.backendbuhoseat.repository.SubReviewRepository;
import com.example.eeza.backendbuhoseat.services.LocalService;
import com.example.eeza.backendbuhoseat.services.ReviewService;
import com.example.eeza.backendbuhoseat.services.SubReviewService;
import com.example.eeza.backendbuhoseat.services.UserService;
import com.example.eeza.backendbuhoseat.utils.mappers.LocalMapper;
import com.example.eeza.backendbuhoseat.utils.mappers.ReviewMapper;
import com.example.eeza.backendbuhoseat.utils.mappers.SubReviewMapper;
import com.example.eeza.backendbuhoseat.utils.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.example.eeza.backendbuhoseat.utils.Constants.ENTITY_SUBREVIEW;
import static com.example.eeza.backendbuhoseat.utils.Constants.EXISTS;

@Service
@RequiredArgsConstructor
public class SubReviewServiceImpl implements SubReviewService {
    private final SubReviewRepository subReviewRepository;
    private final UserService userService;
    private final ReviewService reviewService;
    private final LocalService localService;

    @Override
    public void createSubReview(CreateSubReviewRequest createSubReviewRequest, UUID localId) {
        boolean  haveSubReview = subReviewRepository
                .existsByUser_IdAndReview_Id(createSubReviewRequest.getIdUser(), createSubReviewRequest.getIdReview());

        if (haveSubReview)
            throw new SubReviewAlreadyExistException(ENTITY_SUBREVIEW+EXISTS);

        UserResponse user = userService.getUserById(createSubReviewRequest.getIdUser());
        ReviewResponse review = reviewService.findById(createSubReviewRequest.getIdReview());
        LocalResponse local = localService.getLocalById(localId);

        subReviewRepository.save(
                SubReviewMapper.
                        toEntityCreate(
                                createSubReviewRequest,
                                ReviewMapper.toEntity(review, UserMapper.toEntity(user), LocalMapper.toEntity(local)),
                                UserMapper.toEntity(user)
                        )
        );
    }

    public List<SubReviewResponse> getSubReviewsByLocal(UUID id) {
        return null;
    }
    public List<SubReviewResponse> getSubReviewsByUserId(UUID id) {
        return null;
    }
    public void updateSubReview(UUID id, UpdateSubReviewRequest updateSubReviewRequest) {
    }
    public void deleteSubReview(UUID id) {

    }
}
