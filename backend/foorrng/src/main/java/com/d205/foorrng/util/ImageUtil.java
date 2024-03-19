package com.d205.foorrng.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
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

    private AmazonS3 amazonS3;

    private AmazonS3Client amazonS3Client;

    // 기능 : 이미지 S3에 저장
    // 기능 : S3에 저장된 이미지 Url string 타입으로 반환
    public static String saveImageS3(String imgFileName, String dir, MultipartFile picture) throws IOException{
        String imgUrl = "";

        System.out.println("picture");
        System.out.println(picture.getContentType());
        System.out.println(picture.getSize());

        // 이미지 메타 데이터 설정
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(picture.getSize());


        try {
            System.out.println(imgFileName + dir + picture.getName());

            // S3에 이미지저장
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket + dir, imgFileName, picture.getInputStream(), objectMetadata);
            amazonS3.putObject(putObjectRequest);

//            amazonS3Client.putObject(bucket + dir, imgFileName, picture.getInputStream(), objectMetadata);
            imgUrl = amazonS3Client.getUrl(bucket + dir, imgFileName).toString();
            System.out.println("이미지 업로드 성공");

            return imgUrl;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("이미지 업로드 실패", e);
        }
    }
}
