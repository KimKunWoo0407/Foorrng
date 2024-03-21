package com.d205.foorrng.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.d205.foorrng.common.exception.Exceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Component // 알아서 빈객체 생성 및 관리 해줌
@RequiredArgsConstructor
public class ImageSave {

    private final AmazonS3Client amazonS3Client;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

//    @Autowired // 알아서 생성 안해도, 해당 메서드 사용할 수 있게 해주는 거 같음.
//    public ImageSave(AmazonS3Client amazonS3Client, @Value("${cloud.aws.s3.bucket}") String bucket){
//        this.amazonS3Client = amazonS3Client;
//        this.bucket = bucket;
//    }

    public String saveImageS3(MultipartFile picture, String imgName, String dir) throws IOException {
        String imgUrl = "";

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(picture.getSize());

        try {
            amazonS3Client.putObject(bucket+dir, imgName, picture.getInputStream(), objectMetadata);
            imgUrl = amazonS3Client.getUrl(bucket+dir, imgName).toString();
            return imgUrl;
        }
        catch(Exception e){
            e.printStackTrace();;
            throw new IOException("이미지 업로드 실패", e);
        }
    }
}
