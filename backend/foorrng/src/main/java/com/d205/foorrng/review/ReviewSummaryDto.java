package com.d205.foorrng.review;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewSummaryDto {
    private Long totalReviews;
    private Long rvIsDelicious;
    private Long isCool;
    private Long isClean;
    private Long isKind;
    private Long rvIdSpecial;
    private Long rvIsChip;
    private Long rvIsFast;
}

