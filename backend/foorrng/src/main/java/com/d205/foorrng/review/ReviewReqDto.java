package com.d205.foorrng.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewReqDto {
    @NotNull
    private Boolean rvIsDelicious;
    @NotNull
    private Boolean isCool;
    @NotNull
    private Boolean isClean;
    @NotNull
    private Boolean isKind;
    @NotNull
    private Boolean rvIdSpecial;
    @NotNull
    private Boolean rvIsChip;
    @NotNull
    private Boolean rvIsFast;
}
