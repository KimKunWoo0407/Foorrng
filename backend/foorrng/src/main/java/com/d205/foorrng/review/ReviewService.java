package com.d205.foorrng.review;

public interface ReviewService {
    // 리뷰 생성
    ReviewResDto createReview(Long foodtrucks_seq, ReviewReqDto reviewReqDto);

    // 리뷰 조회
     ReviewSummaryDto getReviews(Long foodtrucks_seq);
}
