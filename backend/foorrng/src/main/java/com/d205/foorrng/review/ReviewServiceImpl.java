package com.d205.foorrng.review;

import com.d205.foorrng.common.exception.ErrorCode;
import com.d205.foorrng.common.exception.Exceptions;
import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import com.d205.foorrng.foodtruck.repository.FoodtrucksRepository;
import com.d205.foorrng.user.entity.User;
import com.d205.foorrng.user.repository.UserRepository;
import com.d205.foorrng.util.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final FoodtrucksRepository foodtrucksRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Override
    public ReviewResDto createReview(Long foodtrucks_seq, ReviewReqDto reviewReqDto) {

        // 현재 로그인한 사용자 정보를 가져옴
        User user = userRepository.findByUserUid(Long.parseLong(SecurityUtil.getCurrentUsername().get())).get();

        // 사용자의 초신 리뷰 조회
        Optional<Review> lastReview = reviewRepository.findTopByUserOrderByCreatedDateDesc(user);

        if(lastReview.isPresent()){
            // 마지막 리뷰를 작성한 날짜와 오늘 날짜를 비교
            LocalDateTime lastReviewDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(lastReview.get().getCreatedDate()), ZoneId.systemDefault());
            LocalDate today = LocalDate.now();

            // 이미 오늘 리뷰를 작성했다면 예외처리
            if(lastReviewDate.toLocalDate().isEqual(today)){
                throw new Exceptions(ErrorCode.REVIEW_TODAY_EXIST);
            }
        }

        // 푸드트럭 정보 가져옴
        Foodtrucks foodtrucks = foodtrucksRepository.findById(foodtrucks_seq)
                .orElseThrow(() -> new Exceptions(ErrorCode.FOODTRUCK_NOT_EXIST));

        // 생성 시간
        Long createdDate = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        // 리뷰 저장하기
        Review review = Review.builder()
                .rvIsDelicious(reviewReqDto.getRvIsDelicious())
                .isCool(reviewReqDto.getIsCool())
                .isClean(reviewReqDto.getIsClean())
                .isKind(reviewReqDto.getIsKind())
                .rvIdSpecial(reviewReqDto.getRvIdSpecial())
                .rvIsChip(reviewReqDto.getRvIsChip())
                .rvIsFast(reviewReqDto.getRvIsFast())
                .createdDate(createdDate)
                .user(user)
                .foodtrucks(foodtrucks)
                .build();
        reviewRepository.save(review);

        return ReviewResDto.fromEntity(review);
    }

    @Override
    public ReviewSummaryDto getReviews(Long foodtrucks_seq) {
        List<Review> reviews = reviewRepository.findByFoodtrucksId(foodtrucks_seq);

        ReviewSummaryDto summary = new ReviewSummaryDto();

        // 총 리뷰 개수 설정
        summary.setTotalReviews((long) reviews.size());

        // 각 항목별 리뷰 개수를 집계
        summary.setRvIsDelicious(reviews.stream().filter(Review::getRvIsDelicious).count());
        summary.setIsCool(reviews.stream().filter(Review::getIsCool).count());
        summary.setIsClean(reviews.stream().filter(Review::getIsClean).count());
        summary.setIsKind(reviews.stream().filter(Review::getIsKind).count());
        summary.setRvIdSpecial(reviews.stream().filter(Review::getRvIdSpecial).count());
        summary.setRvIsChip(reviews.stream().filter(Review::getRvIsChip).count());
        summary.setRvIsFast(reviews.stream().filter(Review::getRvIsFast).count());
        return summary;
    }

    @Override
    public List<Map<String,Object>> getReviewlist(Long foodtruckId){
        List<Map<String,Object>> reviews = new ArrayList<>();

        Map<String, Object> review1 = new HashMap<>();
        review1.put("Id", "rvIsDelicious");
        review1.put("cnt", reviewRepository.countDeliciousReviewsByFoodtruckId(foodtruckId));

        Map<String, Object> review2 = new HashMap<>();
        review2.put("Id", "rvIdSpecial");
        review2.put("cnt", reviewRepository.countSpecialReviewsByFoodtruckId(foodtruckId));

        Map<String, Object> review3 = new HashMap<>();
        review3.put("Id", "rvIsChip");
        review3.put("cnt", reviewRepository.countChipReviewsByFoodtruckId(foodtruckId));

        Map<String, Object> review4 = new HashMap<>();
        review4.put("Id", "rvIsFast");
        review4.put("cnt", reviewRepository.countFastReviewsByFoodtruckId(foodtruckId));

        Map<String, Object> review5 = new HashMap<>();
        review5.put("Id", "isClean");
        review5.put("cnt", reviewRepository.countCleanReviewsByFoodtruckId(foodtruckId));

        Map<String, Object> review6 = new HashMap<>();
        review6.put("Id", "isCool");
        review6.put("cnt", reviewRepository.countCoolReviewsByFoodtruckId(foodtruckId));

        Map<String, Object> review7 = new HashMap<>();
        review7.put("Id", "isKind");
        review7.put("cnt", reviewRepository.countKindReviewsByFoodtruckId(foodtruckId));

        reviews.add(review1);
        reviews.add(review2);
        reviews.add(review3);
        reviews.add(review4);
        reviews.add(review5);
        reviews.add(review6);
        reviews.add(review7);

        return reviews;
    };
}
