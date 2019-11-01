package com.alek.influentialpeople.hero.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HeroResponse {

    private String fullName;

    public HeroResponse(String fullName) {
        this.fullName = fullName;
    }
}
