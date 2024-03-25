package com.d205.foorrng.festival.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFestival is a Querydsl query type for Festival
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFestival extends EntityPathBase<Festival> {

    private static final long serialVersionUID = -1790169814L;

    public static final QFestival festival = new QFestival("festival");

    public final StringPath country = createString("country");

    public final StringPath festivalName = createString("festivalName");

    public final NumberPath<Long> finishDay = createNumber("finishDay", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> startDay = createNumber("startDay", Long.class);

    public QFestival(String variable) {
        super(Festival.class, forVariable(variable));
    }

    public QFestival(Path<? extends Festival> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFestival(PathMetadata metadata) {
        super(Festival.class, metadata);
    }

}

