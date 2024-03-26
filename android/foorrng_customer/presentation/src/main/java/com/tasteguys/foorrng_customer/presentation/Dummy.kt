package com.tasteguys.foorrng_customer.presentation

import com.tasteguys.foorrng_customer.presentation.model.Review
import com.tasteguys.foorrng_customer.presentation.model.TruckInfo
import com.tasteguys.foorrng_customer.presentation.model.TruckMark
import com.tasteguys.foorrng_customer.presentation.model.TruckMenu
import com.tasteguys.foorrng_customer.presentation.model.TruckOperationInfo
import com.tasteguys.foorrng_customer.presentation.model.TruckReview
import com.tasteguys.foorrng_customer.presentation.model.TruckWithFavorite

object Dummy{
    val category = mutableListOf(
        "햄버거", "아이스크림", "곱창 & 막창",
        "치킨", "디저트 & 커피", "분식",
        "케밥 & 타코", "닭꼬치", "핫도그",
        "타코야끼", "츄러스", "스테이크"
    )

    val trucks = mutableListOf(
        TruckWithFavorite(
            0, 0, "야미족발", "", true, 17, 17, listOf(
                "족발"
            )
        ),
        TruckWithFavorite(
            0, 0, "야미족발2", "", false, 18, 18, listOf(
                "치킨"
            )
        ),
    )

    val truckInfo = TruckInfo(
        0, "야미족발", "", listOf("치킨"), "12가1234", "010-1234-1234",
        "", "1234-1234-1234", mutableListOf(
            TruckMenu("닭꼬치", 3000, ""),
            TruckMenu("떡꼬치", 3000, ""),
            TruckMenu("산적꼬치", 3000, ""),
            TruckMenu("은행꼬치", 3000, ""),
            TruckMenu("파꼬치", 3000, ""),
        ), TruckReview(
            35, mutableListOf(
                Review("음식이 맛있어요", 5, 35),
                Review("특별한 메뉴가 있어요", 5, 35),
                Review("가성비가 좋아요", 5, 35),
                Review("음식이 빨리 나와요", 5, 35),
                Review("푸드트럭이 멋져요", 5, 35),
                Review("매장이 청결해요", 5, 35),
                Review("사장님이 친절해요", 5, 35)
            )
        )
    )

    val markInfo = mutableListOf(
        TruckMark(
            0, 0, 0, 0, "대구광역시 중구 명륜로 23길 80", mutableListOf(
                TruckOperationInfo(0, "화", 0, 0),
                TruckOperationInfo(0, "금", 0, 0),
            )

        ),
        TruckMark(
            0, 0, 0, 0, "대구광역시 중구 명륜로 24길", mutableListOf(
                TruckOperationInfo(0, "월", 0, 0),
                TruckOperationInfo(0, "수", 0, 0),
            )

        )

    )

}