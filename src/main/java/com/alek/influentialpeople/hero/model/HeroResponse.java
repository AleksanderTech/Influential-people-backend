package com.alek.influentialpeople.hero.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeroResponse {

    private String fullName;
    private String profileImageUrl;
    private int score;

    public HeroResponse(String fullName) {
        this.fullName = fullName;
    }
}
