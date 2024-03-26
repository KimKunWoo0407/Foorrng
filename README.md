Team D205 project readme
@Getter
public class FoodtruckResDto {
// 오타를 수정한 필드명
private Long foodtruckId;
// ... 다른 필드들

    public FoodtruckResDto(Foodtruck foodtruck) {
        this.foodtruckId = foodtruck.getId();
        this.announcement = foodtruck.getAnnouncement();
        this.createdDay = foodtruck.getCreatedDay(); // 등록일 필드가 있다면 추가
        // ... 다른 필드들 초기화
    }

    // Overloaded 생성자, 만약 필요하다면
    public FoodtruckResDto(Long foodtruckId, String announcement, Long createdDay, String picture, String name, String accountInfo, String carNumber, String phoneNumber) {
        this.foodtruckId = foodtruckId;
        this.announcement = announcement;
        this.createdDay = createdDay;
        // ... 다른 필드들 초기화
    }

    public static FoodtruckResDto fromEntity(Foodtruck foodtruck) {
        return new FoodtruckResDto(
                foodtruck.getId(),
                foodtruck.getAnnouncement(),
                // 다른 필드들도 이와 같은 방식으로 초기화
        );
    }
}