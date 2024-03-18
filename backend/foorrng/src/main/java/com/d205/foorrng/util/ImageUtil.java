package com.d205.foorrng.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.d205.foorrng.foodtruck.entity.Foodtrucks;
import com.d205.foorrng.foodtruck.request.FoodtruckCreateDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Getter
@UtilityClass
public class ImageUtil {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    public AmazonS3Client amazonS3Client;

    // 이미지 S3에 저장
    // S3에 저장된 이미지 Url string 타입으로 반환
    public static String saveImageS3(Foodtrucks foodtrucks, FoodtruckCreateDto foodtruckCreateDto, MultipartFile picture) throws IOException{
        String imgUrl = "";

        // 이미지 메타 데이터 설정
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentEncoding(picture.getContentType());
        objectMetadata.setContentLength(picture.getSize());

        // S3에 저장할 이미지 파일명
        String imgName = "foodtruckIMG/" + foodtruckCreateDto.getName() + "/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HHmmss"));
        String key = "foodtruckIMG/" + foodtruckCreateDto.getName() + "/" + foodtrucks.getId();

        try {
            // S3에 이미지저장
            amazonS3Client.putObject(bucket, key, picture.getInputStream(), objectMetadata);
            imgUrl = amazonS3Client.getUrl(bucket, key).toString();
            return imgUrl;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("이미지 업로드 실패", e);
        }
    }
}
