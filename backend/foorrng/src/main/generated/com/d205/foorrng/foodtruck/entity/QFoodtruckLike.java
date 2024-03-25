package com.d205.foorrng.foodtruck.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFoodtruckLike is a Querydsl query type for FoodtruckLike
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodtruckLike extends EntityPathBase<FoodtruckLike> {

    private static final long serialVersionUID = -1525030729L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFoodtruckLike foodtruckLike = new QFoodtruckLike("foodtruckLike");

    public final QFoodtrucks foodtrucks;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.d205.foorrng.user.entity.QUser user;

    public QFoodtruckLike(String variable) {
        this(FoodtruckLike.class, forVariable(variable), INITS);
    }

    public QFoodtruckLike(Path<? extends FoodtruckLike> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFoodtruckLike(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFoodtruckLike(PathMetadata metadata, PathInits inits) {
        this(FoodtruckLike.class, metadata, inits);
    }

    public QFoodtruckLike(Class<? extends FoodtruckLike> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.foodtrucks = inits.isInitialized("foodtrucks") ? new QFoodtrucks(forProperty("foodtrucks"), inits.get("foodtrucks")) : null;
        this.user = inits.isInitialized("user") ? new com.d205.foorrng.user.entity.QUser(forProperty("user")) : null;
    }

}

