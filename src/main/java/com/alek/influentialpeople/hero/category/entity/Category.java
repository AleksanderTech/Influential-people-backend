package com.alek.influentialpeople.hero.category.entity;

import com.alek.influentialpeople.common.Urls;
import com.alek.influentialpeople.hero.entity.Hero;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category {

    @Id
    private String name;
    private String description;
    @ManyToMany(mappedBy = "heroCategories")
    private Set<Hero> hero = new HashSet<>();
    private String imagePath;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String buildUrl(String serverUrl) {
         return serverUrl + Urls.CATEGORY + "/" + this.name + Urls.IMAGE;
     }
}
