package com.d205.foorrng.foodtruck.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFoodtruckId is a Querydsl query type for FoodtruckId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QFoodtruckId extends BeanPath<FoodtruckId> {

    private static final long serialVersionUID = -247396805L;

    public static final QFoodtruckId foodtruckId = new QFoodtruckId("foodtruckId");

    public final NumberPath<Long> foodtrucksId = createNumber("foodtrucksId", Long.class);

    public QFoodtruckId(String variable) {
        super(FoodtruckId.class, forVariable(variable));
    }

    public QFoodtruckId(Path<? extends FoodtruckId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFoodtruckId(PathMetadata metadata) {
        super(FoodtruckId.class, metadata);
    }

}

