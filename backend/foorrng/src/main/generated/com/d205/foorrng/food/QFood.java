package com.d205.foorrng.food;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFood is a Querydsl query type for Food
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFood extends EntityPathBase<Food> {

    private static final long serialVersionUID = -1975304367L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFood food = new QFood("food");

    public final com.d205.foorrng.foodtruck.entity.QFoodtrucks foodtrucks;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public QFood(String variable) {
        this(Food.class, forVariable(variable), INITS);
    }

    public QFood(Path<? extends Food> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFood(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFood(PathMetadata metadata, PathInits inits) {
        this(Food.class, metadata, inits);
    }

    public QFood(Class<? extends Food> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.foodtrucks = inits.isInitialized("foodtrucks") ? new com.d205.foorrng.foodtruck.entity.QFoodtrucks(forProperty("foodtrucks"), inits.get("foodtrucks")) : null;
    }

}

