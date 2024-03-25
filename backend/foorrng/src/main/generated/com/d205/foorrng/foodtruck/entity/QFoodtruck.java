package com.d205.foorrng.foodtruck.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFoodtruck is a Querydsl query type for Foodtruck
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodtruck extends EntityPathBase<Foodtruck> {

    private static final long serialVersionUID = 1465662720L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFoodtruck foodtruck = new QFoodtruck("foodtruck");

    public final StringPath accountInfo = createString("accountInfo");

    public final StringPath announcement = createString("announcement");

    public final StringPath carNumber = createString("carNumber");

    public final NumberPath<Long> createdDay = createNumber("createdDay", Long.class);

    public final QFoodtruckId foodtruckId;

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath picture = createString("picture");

    public QFoodtruck(String variable) {
        this(Foodtruck.class, forVariable(variable), INITS);
    }

    public QFoodtruck(Path<? extends Foodtruck> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFoodtruck(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFoodtruck(PathMetadata metadata, PathInits inits) {
        this(Foodtruck.class, metadata, inits);
    }

    public QFoodtruck(Class<? extends Foodtruck> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.foodtruckId = inits.isInitialized("foodtruckId") ? new QFoodtruckId(forProperty("foodtruckId")) : null;
    }

}

