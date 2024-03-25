package com.d205.foorrng.review;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = 50744465L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final NumberPath<Long> createdDate = createNumber("createdDate", Long.class);

    public final com.d205.foorrng.foodtruck.entity.QFoodtrucks foodtrucks;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isClean = createBoolean("isClean");

    public final BooleanPath isCool = createBoolean("isCool");

    public final BooleanPath isKind = createBoolean("isKind");

    public final BooleanPath rvIdSpecial = createBoolean("rvIdSpecial");

    public final BooleanPath rvIsChip = createBoolean("rvIsChip");

    public final BooleanPath rvIsDelicious = createBoolean("rvIsDelicious");

    public final BooleanPath rvIsFast = createBoolean("rvIsFast");

    public final com.d205.foorrng.user.entity.QUser user;

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.foodtrucks = inits.isInitialized("foodtrucks") ? new com.d205.foorrng.foodtruck.entity.QFoodtrucks(forProperty("foodtrucks"), inits.get("foodtrucks")) : null;
        this.user = inits.isInitialized("user") ? new com.d205.foorrng.user.entity.QUser(forProperty("user")) : null;
    }

}

