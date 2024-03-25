package com.d205.foorrng.requestDelete;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRequestDelete is a Querydsl query type for RequestDelete
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRequestDelete extends EntityPathBase<RequestDelete> {

    private static final long serialVersionUID = 634506153L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRequestDelete requestDelete = new QRequestDelete("requestDelete");

    public final com.d205.foorrng.foodtruck.entity.QFoodtrucks foodtrucks;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.d205.foorrng.user.entity.QUser user;

    public QRequestDelete(String variable) {
        this(RequestDelete.class, forVariable(variable), INITS);
    }

    public QRequestDelete(Path<? extends RequestDelete> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRequestDelete(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRequestDelete(PathMetadata metadata, PathInits inits) {
        this(RequestDelete.class, metadata, inits);
    }

    public QRequestDelete(Class<? extends RequestDelete> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.foodtrucks = inits.isInitialized("foodtrucks") ? new com.d205.foorrng.foodtruck.entity.QFoodtrucks(forProperty("foodtrucks"), inits.get("foodtrucks")) : null;
        this.user = inits.isInitialized("user") ? new com.d205.foorrng.user.entity.QUser(forProperty("user")) : null;
    }

}

