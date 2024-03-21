package com.d205.foorrng.article.service;

import com.d205.foorrng.article.dto.request.ArticleReqDto;
import com.d205.foorrng.article.dto.response.ArticleListResDto;
import com.d205.foorrng.article.dto.response.ArticleResDto;
import com.d205.foorrng.article.entity.Article;
import com.d205.foorrng.article.repository.ArticlePostRepository;
import com.d205.foorrng.common.model.BaseResponseBody;
import com.d205.foorrng.common.service.S3Service;
import com.d205.foorrng.user.entity.User;
import com.d205.foorrng.user.repository.UserRepository;
import com.d205.foorrng.util.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.tags.ArgumentTag;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleServiceImpl implements ArticleService{
    private final S3Service s3Service;
    private final ArticlePostRepository articlePostRepository;
    private final UserRepository userRepository;


    @Transactional
    public ResponseEntity<BaseResponseBody> saveArticle(ArticleReqDto article, MultipartFile mainImage) {
        try {
            String mainImgUrl = "";
            if (mainImage != null && !mainImage.isEmpty()) {
                mainImgUrl = s3Service.uploadS3(mainImage, "images");
            }
            Optional<String> currentUsername = SecurityUtil.getCurrentUsername();
            if (!currentUsername.isPresent()) {
                // 현재 사용자 이름을 가져오는 데 실패한 경우
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(BaseResponseBody.of(1, "사용자 인증 실패"));
            }
            Long userId = Long.parseLong(currentUsername.get());
            Optional<User> userOptional = userRepository.findByUserUid(userId);
            if (!userOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(BaseResponseBody.of(1, "사용자를 찾을 수 없음"));
            }
            User user = userOptional.get();
            Article articleForSave = Article.builder()
                    .user(user)
                    .address(article.getAddress())
                    .phone(article.getPhone())
                    .title(article.getTitle())
                    .content(article.getContent())
                    .latitude(article.getLatitude())
                    .longitude(article.getLongitude())
                    .email(article.getEmail())
                    .kakaoID(article.getKakaoId())
                    .organizer(article.getOrganizer())
                    .startDate(article.getStartDate())
                    .endDate(article.getEndDate())
                    .picture(mainImgUrl)
                    .build();
            articlePostRepository.save(articleForSave);
            return ResponseEntity.status(HttpStatus.CREATED).body(BaseResponseBody.of(0, "성공적으로 저장"));
        } catch (NumberFormatException e) {
            // 사용자 ID 변환 중 예외 발생
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(BaseResponseBody.of(1, "잘못된 사용자 ID"));
        } catch (Exception e) {
            // 기타 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(1, "서버 내부 오류: " + e.getMessage()));
        }
    }


    public List<Article> findAllArticle(){
        return articlePostRepository.findAll();
    }

    public Optional<Article> findByArticle(Long articleId) {
        return articlePostRepository.findById(articleId);
    }


    public Optional<ArticleResDto> getArticle(Long id) {
        Optional<Article> article;
        Optional<ArticleResDto> articleResDto;
        article = articlePostRepository.findById(id);
        if (article.isPresent()){

        }
        return null;
    }

    private ArticleListResDto entityToDto(Article article) {
        return new ArticleListResDto(
                article.getId(),
                article.getTitle(),
                article.getContent(),
                article.getLatitude(),
                article.getLongitude(),
                article.getAddress(),
                article.getPicture()
        );
    }

    public List<ArticleListResDto> getArticleList() {
        List<Article> articles = articlePostRepository.findAll();
        return articles.stream().map(this::entityToDto).collect(Collectors.toList());
    }


}
