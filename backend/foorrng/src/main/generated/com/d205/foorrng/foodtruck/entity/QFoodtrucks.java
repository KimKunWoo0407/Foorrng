package com.d205.foorrng.foodtruck.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFoodtrucks is a Querydsl query type for Foodtrucks
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFoodtrucks extends EntityPathBase<Foodtrucks> {

    private static final long serialVersionUID = -1809095821L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFoodtrucks foodtrucks = new QFoodtrucks("foodtrucks");

    public final ListPath<com.d205.foorrng.food.Food, com.d205.foorrng.food.QFood> foods = this.<com.d205.foorrng.food.Food, com.d205.foorrng.food.QFood>createList("foods", com.d205.foorrng.food.Food.class, com.d205.foorrng.food.QFood.class, PathInits.DIRECT2);

    public final ListPath<FoodtruckLike, QFoodtruckLike> foodtruckLikes = this.<FoodtruckLike, QFoodtruckLike>createList("foodtruckLikes", FoodtruckLike.class, QFoodtruckLike.class, PathInits.DIRECT2);

    public final EnumPath<FoodtruckRole> foodtruckRole = createEnum("foodtruckRole", FoodtruckRole.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<com.d205.foorrng.mark.Mark, com.d205.foorrng.mark.QMark> markList = this.<com.d205.foorrng.mark.Mark, com.d205.foorrng.mark.QMark>createList("markList", com.d205.foorrng.mark.Mark.class, com.d205.foorrng.mark.QMark.class, PathInits.DIRECT2);

    public final ListPath<com.d205.foorrng.menu.Menu, com.d205.foorrng.menu.QMenu> menuList = this.<com.d205.foorrng.menu.Menu, com.d205.foorrng.menu.QMenu>createList("menuList", com.d205.foorrng.menu.Menu.class, com.d205.foorrng.menu.QMenu.class, PathInits.DIRECT2);

    public final ListPath<com.d205.foorrng.requestDelete.RequestDelete, com.d205.foorrng.requestDelete.QRequestDelete> requestDeleteList = this.<com.d205.foorrng.requestDelete.RequestDelete, com.d205.foorrng.requestDelete.QRequestDelete>createList("requestDeleteList", com.d205.foorrng.requestDelete.RequestDelete.class, com.d205.foorrng.requestDelete.QRequestDelete.class, PathInits.DIRECT2);

    public final com.d205.foorrng.user.entity.QUser user;

    public QFoodtrucks(String variable) {
        this(Foodtrucks.class, forVariable(variable), INITS);
    }

    public QFoodtrucks(Path<? extends Foodtrucks> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFoodtrucks(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFoodtrucks(PathMetadata metadata, PathInits inits) {
        this(Foodtrucks.class, metadata, inits);
    }

    public QFoodtrucks(Class<? extends Foodtrucks> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new com.d205.foorrng.user.entity.QUser(forProperty("user")) : null;
    }

}

