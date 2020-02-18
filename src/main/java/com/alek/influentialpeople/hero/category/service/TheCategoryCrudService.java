package com.alek.influentialpeople.hero.category.service;

import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.category.persistence.CategoryCrudRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;

@Service
public class TheCategoryCrudService implements CategoryCrudService {

    private CategoryCrudRepository categoryCrudRepository;

    public TheCategoryCrudService(CategoryCrudRepository categoryCrudRepository) {
        this.categoryCrudRepository = categoryCrudRepository;
    }

    @Override
    public Collection<Category> findCategories() {
        return categoryCrudRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return categoryCrudRepository.save(category);
    }

    @Override
    public Category findCategory(String name) {
        return categoryCrudRepository.findById(name).get();
    }

    @Override
    public String deleteCategory(String name) {
        categoryCrudRepository.deleteById(name);
        return name;
    }

    @Override
    public Category updateCategory(String name, Category changes) {
        Optional<Category> optionalCat = categoryCrudRepository.findById(name);
        if (optionalCat.isPresent()) {
            Category category = optionalCat.get();
            category = setChanges(category,changes);
            return categoryCrudRepository.save(category);
        }
        throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_CATEGORY_MESSAGE);
    }

    private Category setChanges(Category category, Category changes) {
        if (!category.getDescription().equals(changes.getDescription())) {
            category.setDescription(changes.getDescription());
            return category;
        }
        return category;
    }
}
