package com.alek.influentialpeople.hero.category.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryChanges {

    private String description;

    public CategoryChanges(String description) {
        this.description = description;
    }
}

