package com.d205.foorrng.food.repository;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FavoritefoodList {

    HAMBURGER("햄버거"),
    ICECREAM("아이스크림"),
    GOPCHANG_MAKCHANG("곱창 & 막창"),
    CHICKEN("치킨"),
    DESSERT_COFFEE("디저트 & 커피"),
    SCHOOLFOOD("분식"),
    KEBAB_TACO("케밥 & 타코"),
    CHICKENSKEWERS("닭꼬치"),
    HOTDOG("핫도그"),
    TAKOYAKI("타코야끼"),
    CHURROS("츄러스"),
    STEAK("스테이크");

    private final String menu;

}
