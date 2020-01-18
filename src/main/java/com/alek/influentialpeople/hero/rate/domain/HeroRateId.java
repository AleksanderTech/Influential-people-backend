package com.alek.influentialpeople.hero.rate.domain;

import lombok.AllArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
public class HeroRateId implements Serializable {

    @Column(name = "user_id")
    private String userId;
    @Column(name = "hero_id")
    private String heroId;

}
