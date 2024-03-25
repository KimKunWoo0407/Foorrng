package com.d205.foorrng.foodtruck.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFoodtruckReport is a Querydsl query type for FoodtruckReport
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodtruckReport extends EntityPathBase<FoodtruckReport> {

    private static final long serialVersionUID = -802439596L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFoodtruckReport foodtruckReport = new QFoodtruckReport("foodtruckReport");

    public final StringPath accountInfo = createString("accountInfo");

    public final StringPath announcement = createString("announcement");

    public final StringPath carNumber = createString("carNumber");

    public final NumberPath<Long> createdDay = createNumber("createdDay", Long.class);

    public final QFoodtruckReportId foodtruckId;

    public final StringPath name = createString("name");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final StringPath picture = createString("picture");

    public QFoodtruckReport(String variable) {
        this(FoodtruckReport.class, forVariable(variable), INITS);
    }

    public QFoodtruckReport(Path<? extends FoodtruckReport> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFoodtruckReport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFoodtruckReport(PathMetadata metadata, PathInits inits) {
        this(FoodtruckReport.class, metadata, inits);
    }

    public QFoodtruckReport(Class<? extends FoodtruckReport> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.foodtruckId = inits.isInitialized("foodtruckId") ? new QFoodtruckReportId(forProperty("foodtruckId")) : null;
    }

}

