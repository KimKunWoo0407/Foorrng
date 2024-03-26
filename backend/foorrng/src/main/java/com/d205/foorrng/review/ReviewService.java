package com.d205.foorrng.review;

<<<<<<< HEAD
=======
import java.util.List;
import java.util.Map;

>>>>>>> 858941140174e122ab62f24667d48b67677a581c
public interface ReviewService {
    // 리뷰 생성
    ReviewResDto createReview(Long foodtrucks_seq, ReviewReqDto reviewReqDto);

    // 리뷰 조회
     ReviewSummaryDto getReviews(Long foodtrucks_seq);

     // 리뷰 조회 - 안드 요청
    List<Map<String,Object>> getReviewlist(Long foodtruckId);
}
