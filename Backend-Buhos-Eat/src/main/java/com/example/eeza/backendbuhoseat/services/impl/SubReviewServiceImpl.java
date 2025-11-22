package com.example.eeza.backendbuhoseat.services.impl;

import com.example.eeza.backendbuhoseat.domain.dto.request.subreview.CreateSubReviewRequest;
import com.example.eeza.backendbuhoseat.domain.dto.request.subreview.UpdateSubReviewRequest;
import com.example.eeza.backendbuhoseat.domain.dto.response.local.LocalResponse;
import com.example.eeza.backendbuhoseat.domain.dto.response.review.ReviewResponse;
import com.example.eeza.backendbuhoseat.domain.dto.response.subreview.SubReviewResponse;
import com.example.eeza.backendbuhoseat.domain.dto.response.user.UserResponse;
import com.example.eeza.backendbuhoseat.domain.entities.SubReview;
import com.example.eeza.backendbuhoseat.exception.ReviewNotFoundException;
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

import static com.example.eeza.backendbuhoseat.utils.Constants.*;

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

    public List<SubReviewResponse> getSubReviewsByReview(UUID id) {
        List<SubReviewResponse> subReview = SubReviewMapper.toDtoListResponse(subReviewRepository.findAllByReview_Id(id));

        if(subReview.isEmpty())
            throw new ReviewNotFoundException(ENTITY_SUBREVIEW+NOT_FOUND);

        return subReview;
    }

    public List<SubReviewResponse> getSubReviewsByUserId(UUID id) {
        List<SubReviewResponse> subReview = SubReviewMapper.toDtoListResponse(subReviewRepository.findAllByUser_Id(id));

        if(subReview.isEmpty())
            throw new ReviewNotFoundException(ENTITY_SUBREVIEW+NOT_FOUND);

        return subReview;
    }

    public void updateSubReview(UpdateSubReviewRequest updateSubReviewRequest) {
        SubReview subReview = subReviewRepository
                .findById(updateSubReviewRequest.getIdSubReview()).orElseThrow(
                        () -> new ReviewNotFoundException(ENTITY_SUBREVIEW+NOT_FOUND)
                );
        
        if(!updateSubReviewRequest.getContent().isEmpty()) {
            subReview.setContent(updateSubReviewRequest.getContent());
        }

        subReviewRepository.save(subReview);
    }
    public void deleteSubReview(UUID id) {

    }
}
