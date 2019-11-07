package com.alek.influentialpeople.hero.model;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HeroRequest {

    private String fullName;
    private Set<String> categories;

}
