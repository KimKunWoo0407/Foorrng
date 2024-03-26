package com.d205.foorrng.foodtruck.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Getter
public class FoodtruckCreateReqDto {
    @NotBlank
    private String name;
    private MultipartFile picture;
    @NotBlank
    private String carNumber;
    private String announcement;
    private String accountInfo;
    private String phoneNumber;
    private List<String> category;
}
