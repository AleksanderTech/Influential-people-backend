package com.alek.influentialpeople.hero.model;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeroResponse {

    private String fullName;
    private Set<String> categories;
    private int score;

    public HeroResponse(String fullName) {
        this.fullName = fullName;
    }
}
