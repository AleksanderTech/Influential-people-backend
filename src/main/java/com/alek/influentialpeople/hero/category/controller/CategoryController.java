package com.alek.influentialpeople.hero.category.controller;

import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.category.model.CategoryRest;
import com.alek.influentialpeople.hero.category.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CategoryRest> createCategory(@RequestBody CategoryRest categoryRest) {
        Category category = new Category(categoryRest.getName());
        categoryService.createCategory(category);
        return new ResponseEntity<>(categoryRest, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{name}")
    public ResponseEntity<CategoryRest> findCategory(@PathVariable String name) {
        Category category = categoryService.findCategory(name);

        return new ResponseEntity<>(new CategoryRest(name), HttpStatus.OK);
    }
}
