package com.alek.influentialpeople.hero.category.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

     private String name;
     private String description;
     private String imageUrl;

     public CategoryResponse(String name, String description) {
          this.name = name;
          this.description = description;
     }
}
