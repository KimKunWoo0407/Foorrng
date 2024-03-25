package com.d205.foorrng.mark;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMark is a Querydsl query type for Mark
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMark extends EntityPathBase<Mark> {

    private static final long serialVersionUID = -1803981647L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMark mark = new QMark("mark");

    public final StringPath address = createString("address");

    public final com.d205.foorrng.foodtruck.entity.QFoodtrucks foodtrucks;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longitude = createNumber("longitude", Double.class);

    public final ListPath<com.d205.foorrng.operationInfo.OperationInfo, com.d205.foorrng.operationInfo.QOperationInfo> operationInfoList = this.<com.d205.foorrng.operationInfo.OperationInfo, com.d205.foorrng.operationInfo.QOperationInfo>createList("operationInfoList", com.d205.foorrng.operationInfo.OperationInfo.class, com.d205.foorrng.operationInfo.QOperationInfo.class, PathInits.DIRECT2);

    public QMark(String variable) {
        this(Mark.class, forVariable(variable), INITS);
    }

    public QMark(Path<? extends Mark> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMark(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMark(PathMetadata metadata, PathInits inits) {
        this(Mark.class, metadata, inits);
    }

    public QMark(Class<? extends Mark> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.foodtrucks = inits.isInitialized("foodtrucks") ? new com.d205.foorrng.foodtruck.entity.QFoodtrucks(forProperty("foodtrucks"), inits.get("foodtrucks")) : null;
    }

}

