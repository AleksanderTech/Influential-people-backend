package com.alek.influentialpeople.hero.category.entity;

import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
@AllArgsConstructor
public class HeroCategoryId implements Serializable {

    private static final long serialVersionUID = 1434151397030649812L;

    @Column(name = "hero_id")
    private String heroId;
    @Column(name = "category_id")
    private String categoryId;

}
