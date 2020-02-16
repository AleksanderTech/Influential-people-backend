package com.alek.influentialpeople.hero.category.controller;

import com.alek.influentialpeople.common.ConvertersFactory;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.category.model.CategoryResponse;
import com.alek.influentialpeople.hero.category.service.CategoryCrudService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.CATEGORY_TO_CATEGORY_RESPONSE;

@RestController
@RequestMapping("/category")
public class CategoryCrudController {

    private CategoryCrudService categoryCrudService;
    private TwoWayConverter<Category, CategoryResponse> categoryConverter = ConvertersFactory.getConverter(CATEGORY_TO_CATEGORY_RESPONSE);

    public CategoryCrudController(CategoryCrudService categoryCrudService) {
        this.categoryCrudService = categoryCrudService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoryResponse>> findCategories() {

        return new ResponseEntity<>(categoryConverter.convertMany(categoryCrudService.findCategories()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryResponse categoryResponse) {

        return new ResponseEntity<>(categoryConverter.convert(categoryCrudService.addCategory(categoryConverter.convertBack(categoryResponse))), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{name}")
    public ResponseEntity<String> deleteCategory(@PathVariable String name) {

        return new ResponseEntity<>(categoryCrudService.deleteCategory(name), HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{name}")
    public ResponseEntity<CategoryResponse> findCategory(@PathVariable String name) {

        return new ResponseEntity<>(categoryConverter.convert(categoryCrudService.findCategory(name)), HttpStatus.OK);
    }
}
