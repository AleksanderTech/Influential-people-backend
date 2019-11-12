//package com.alek.influentialpeople.hero.category.service;
//
//import com.alek.influentialpeople.hero.category.entity.Category;
//import com.alek.influentialpeople.hero.category.persistence.CategoryRepository;
//import com.alek.influentialpeople.hero.category.persistence.HeroCategoryRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class TheHeroCategoryService implements HeroCategoryService {
//
//    private final CategoryRepository categoryRepository;
//    private final HeroCategoryRepository repository;
//
//    public TheHeroCategoryService(final CategoryRepository categoryRepository, final HeroCategoryRepository repository) {
//        this.categoryRepository = categoryRepository;
//        this.repository = repository;
//    }
//
//    @Override
//    public Category findCategory(String name) {
//        return categoryRepository.findById(name).get();
//    }
//
//    @Override
//    public Category createCategory(Category category) {
//        return categoryRepository.save(category);
//    }
//
//
//
//    @Override
//    public void addCategory(String heroName, String categoryName) {
//
////        repository.addCategory(heroName, categoryName);
//    }
//
//
//    @Override
//    public void deleteCategory(String heroName, String categoryName) {
//
////        repository.deleteCategory(heroName, categoryName);
//    }
//}
