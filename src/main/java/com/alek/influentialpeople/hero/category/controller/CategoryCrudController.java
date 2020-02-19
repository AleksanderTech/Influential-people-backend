package com.alek.influentialpeople.hero.category.controller;

import com.alek.influentialpeople.common.ConvertersFactory;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.common.abstraction.CrudService;
import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.category.model.CategoryChanges;
import com.alek.influentialpeople.hero.category.model.CategoryRequest;
import com.alek.influentialpeople.hero.category.model.CategoryResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.CATEGORY_CHANGES_TO_CATEGORY;
import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.CATEGORY_TO_CATEGORY_RESPONSE;

@RestController
@RequestMapping("/category")
public class CategoryCrudController {

    private final CrudService<Category, String> categoryCrudService;

    private TwoWayConverter<Category, CategoryResponse> categoryConverter = ConvertersFactory.getConverter(CATEGORY_TO_CATEGORY_RESPONSE);
    private TwoWayConverter<CategoryChanges, Category> changesConverter = ConvertersFactory.getConverter(CATEGORY_CHANGES_TO_CATEGORY);

    public CategoryCrudController(@Qualifier("categoryCrudService") CrudService<Category, String> categoryCrudService) {
        this.categoryCrudService = categoryCrudService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoryResponse>> findAll() {
        return new ResponseEntity<>(categoryConverter.convertMany(categoryCrudService.findAll(null).getContent()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest categoryRequest) {
        return new ResponseEntity<>(categoryConverter.convert(categoryCrudService.create(new Category(categoryRequest.getName(), categoryRequest.getDescription()))), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PATCH, value = "/{name}")
    public ResponseEntity<CategoryResponse> update(@RequestBody CategoryChanges changes, @PathVariable String name) {
        return new ResponseEntity<>(categoryConverter.convert(categoryCrudService.update(name, changesConverter.convert(changes))), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{name}")
    public ResponseEntity delete(@PathVariable String name) {
        categoryCrudService.delete(name);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{name}")
    public ResponseEntity<CategoryResponse> findOne(@PathVariable String name) {
        return new ResponseEntity<>(categoryConverter.convert(categoryCrudService.findOne(name)), HttpStatus.OK);
    }
}
