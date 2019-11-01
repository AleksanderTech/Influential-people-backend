package com.alek.influentialpeople.hero.category;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
public class HeroCategoryId implements Serializable {

    private static final long serialVersionUID = 1434151397030649812L;

    @Column(name = "hero_id")
    private int heroId;
    @Column(name = "category_id")
    private int categoryId;

}
