package com.d205.foorrng.user.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -574403376L;

    public static final QUser user = new QUser("user");

    public final StringPath acceessToken = createString("acceessToken");

    public final ListPath<com.d205.foorrng.article.entity.Article, com.d205.foorrng.article.entity.QArticle> articles = this.<com.d205.foorrng.article.entity.Article, com.d205.foorrng.article.entity.QArticle>createList("articles", com.d205.foorrng.article.entity.Article.class, com.d205.foorrng.article.entity.QArticle.class, PathInits.DIRECT2);

    public final StringPath businessNumber = createString("businessNumber");

    public final StringPath email = createString("email");

    public final ListPath<FavoriteFood, QFavoriteFood> favoriteFoods = this.<FavoriteFood, QFavoriteFood>createList("favoriteFoods", FavoriteFood.class, QFavoriteFood.class, PathInits.DIRECT2);

    public final StringPath fcmToken = createString("fcmToken");

    public final ListPath<com.d205.foorrng.foodtruck.entity.FoodtruckLike, com.d205.foorrng.foodtruck.entity.QFoodtruckLike> foodtruckLikes = this.<com.d205.foorrng.foodtruck.entity.FoodtruckLike, com.d205.foorrng.foodtruck.entity.QFoodtruckLike>createList("foodtruckLikes", com.d205.foorrng.foodtruck.entity.FoodtruckLike.class, com.d205.foorrng.foodtruck.entity.QFoodtruckLike.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath refreshToken = createString("refreshToken");

    public final ListPath<com.d205.foorrng.requestDelete.RequestDelete, com.d205.foorrng.requestDelete.QRequestDelete> requestDeleteList = this.<com.d205.foorrng.requestDelete.RequestDelete, com.d205.foorrng.requestDelete.QRequestDelete>createList("requestDeleteList", com.d205.foorrng.requestDelete.RequestDelete.class, com.d205.foorrng.requestDelete.QRequestDelete.class, PathInits.DIRECT2);

    public final EnumPath<com.d205.foorrng.user.repository.UserRole> role = createEnum("role", com.d205.foorrng.user.repository.UserRole.class);

    public final NumberPath<Long> userUid = createNumber("userUid", Long.class);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

