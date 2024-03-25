package com.d205.foorrng.foodtruck.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFoodtruckReportId is a Querydsl query type for FoodtruckReportId
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QFoodtruckReportId extends BeanPath<FoodtruckReportId> {

    private static final long serialVersionUID = 1949663887L;

    public static final QFoodtruckReportId foodtruckReportId = new QFoodtruckReportId("foodtruckReportId");

    public final NumberPath<Long> foodtrucksId = createNumber("foodtrucksId", Long.class);

    public QFoodtruckReportId(String variable) {
        super(FoodtruckReportId.class, forVariable(variable));
    }

    public QFoodtruckReportId(Path<? extends FoodtruckReportId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFoodtruckReportId(PathMetadata metadata) {
        super(FoodtruckReportId.class, metadata);
    }

}

