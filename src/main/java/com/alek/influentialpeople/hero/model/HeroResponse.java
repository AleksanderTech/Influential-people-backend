package com.alek.influentialpeople.hero.model;


import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeroResponse {

    private String name;
    private Set<String> categories;
    private long rate;

    public HeroResponse(String name) {
        this.name = name;
    }
}
