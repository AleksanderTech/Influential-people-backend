package com.alek.influentialpeople.hero.model;

import lombok.*;

import java.util.Set;


@AllArgsConstructor
@Builder
@Setter
@Getter
@NoArgsConstructor
public class HeroDetail {

    private String name;
    private String avatarImageUrl;
    private Set<String> categories;
    private long rate;
}
