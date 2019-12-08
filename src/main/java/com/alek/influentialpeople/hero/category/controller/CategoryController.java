package com.alek.influentialpeople.hero.category.controller;

import com.alek.influentialpeople.common.ConvertersFactory;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.category.model.CategoryRest;
import com.alek.influentialpeople.hero.category.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.CATEGORY_TO_CATEGORY_REST;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;
    private TwoWayConverter<Category, CategoryRest> categoryConverter = ConvertersFactory.getConverter(CATEGORY_TO_CATEGORY_REST);

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoryRest>> findCategories() {

        return new ResponseEntity<>(categoryConverter.convertMany(categoryService.findCategories()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CategoryRest> createCategory(@RequestBody CategoryRest categoryRest) {

        return new ResponseEntity<>(categoryConverter.convert(categoryService.addCategory(categoryConverter.convertBack(categoryRest))), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{name}")
    public ResponseEntity<CategoryRest> findCategory(@PathVariable String name) {

        return new ResponseEntity<>(categoryConverter.convert(categoryService.findCategory(name)), HttpStatus.OK);
    }
}
