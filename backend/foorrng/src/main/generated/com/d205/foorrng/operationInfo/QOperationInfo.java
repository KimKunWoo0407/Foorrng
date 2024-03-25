package com.d205.foorrng.operationInfo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOperationInfo is a Querydsl query type for OperationInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOperationInfo extends EntityPathBase<OperationInfo> {

    private static final long serialVersionUID = 1294183327L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOperationInfo operationInfo = new QOperationInfo("operationInfo");

    public final StringPath day = createString("day");

    public final NumberPath<Long> endTime = createNumber("endTime", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.d205.foorrng.mark.QMark mark;

    public final NumberPath<Long> startTime = createNumber("startTime", Long.class);

    public QOperationInfo(String variable) {
        this(OperationInfo.class, forVariable(variable), INITS);
    }

    public QOperationInfo(Path<? extends OperationInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOperationInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOperationInfo(PathMetadata metadata, PathInits inits) {
        this(OperationInfo.class, metadata, inits);
    }

    public QOperationInfo(Class<? extends OperationInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.mark = inits.isInitialized("mark") ? new com.d205.foorrng.mark.QMark(forProperty("mark"), inits.get("mark")) : null;
    }

}

