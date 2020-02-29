package com.alek.influentialpeople.hero.category.controller;

import com.alek.influentialpeople.common.ImageManager;
import com.alek.influentialpeople.common.ImageType;
import com.alek.influentialpeople.common.Properties;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.common.abstraction.CrudService;
import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.category.model.CategoryChanges;
import com.alek.influentialpeople.hero.category.model.CategoryRequest;
import com.alek.influentialpeople.hero.category.model.CategoryResponse;
import com.alek.influentialpeople.hero.category.service.CategoryChangesConverter;
import com.alek.influentialpeople.hero.category.service.CategoryResponseConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/category")
public class CategoryCrudController {

    private final CrudService<Category, String> categoryCrudService;
    private final Properties properties;

    private TwoWayConverter<Category, CategoryResponse> categoryConverter = new CategoryResponseConverter();
    private TwoWayConverter<CategoryChanges, Category> changesConverter = new CategoryChangesConverter();

    public CategoryCrudController(@Qualifier("categoryCrudService") CrudService<Category, String> categoryCrudService, Properties properties) {
        this.categoryCrudService = categoryCrudService;
        this.properties = properties;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoryResponse>> findAll() {
        List<CategoryResponse> categories = categoryCrudService.findAll(null).getContent().stream()
                .map(cat -> {
                    CategoryResponse response = categoryConverter.convert(cat);
                    response.setImageUrl(ImageManager.createUrl(cat.getImagePath(), properties.getConfig("server.url"), ImageType.CATEGORY, cat.getName()));
                    return response;
                }).collect(Collectors.toList());

        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest categoryRequest) {
        return new ResponseEntity<>(categoryConverter.convert(categoryCrudService.create(new Category(categoryRequest.getName(), categoryRequest.getDescription()))), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.PATCH, value = "/{name}")
    public ResponseEntity<CategoryResponse> update(@RequestBody CategoryChanges changes, @PathVariable String name) {
        return new ResponseEntity<>(categoryConverter.convert(categoryCrudService.update(name, changesConverter.convert(changes))), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{name}")
    public ResponseEntity delete(@PathVariable String name) {
        categoryCrudService.delete(name);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{name}")
    public ResponseEntity<CategoryResponse> findOne(@PathVariable String name) {
        Category category = categoryCrudService.findOne(name);
        CategoryResponse categoryResponse = categoryConverter.convert(category);
        categoryResponse.setImageUrl(ImageManager.createUrl(category.getImagePath(), properties.getConfig("server.url"), ImageType.CATEGORY, category.getName()));
        return new ResponseEntity<>(categoryConverter.convert(categoryCrudService.findOne(name)), HttpStatus.OK);
    }
}
