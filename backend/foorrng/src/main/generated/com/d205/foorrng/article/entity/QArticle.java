package com.d205.foorrng.article.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArticle is a Querydsl query type for Article
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArticle extends EntityPathBase<Article> {

    private static final long serialVersionUID = 892560224L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QArticle article = new QArticle("article");

    public final StringPath address = createString("address");

    public final StringPath content = createString("content");

    public final NumberPath<Long> createdDatetime = createNumber("createdDatetime", Long.class);

    public final StringPath email = createString("email");

    public final NumberPath<Long> endDate = createNumber("endDate", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath kakaoID = createString("kakaoID");

    public final NumberPath<Double> latitude = createNumber("latitude", Double.class);

    public final NumberPath<Double> longtitude = createNumber("longtitude", Double.class);

    public final StringPath organizer = createString("organizer");

    public final StringPath phone = createString("phone");

    public final StringPath picture = createString("picture");

    public final NumberPath<Long> startDate = createNumber("startDate", Long.class);

    public final StringPath title = createString("title");

    public final NumberPath<Long> updeatedDatetime = createNumber("updeatedDatetime", Long.class);

    public final com.d205.foorrng.user.entity.QUser user;

    public QArticle(String variable) {
        this(Article.class, forVariable(variable), INITS);
    }

    public QArticle(Path<? extends Article> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QArticle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QArticle(PathMetadata metadata, PathInits inits) {
        this(Article.class, metadata, inits);
    }

    public QArticle(Class<? extends Article> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.d205.foorrng.user.entity.QUser(forProperty("user")) : null;
    }

}

