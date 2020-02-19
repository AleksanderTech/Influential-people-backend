package com.alek.influentialpeople.hero.category.service;

import com.alek.influentialpeople.common.abstraction.CrudService;
import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.exception.exceptions.EntityExistsException;
import com.alek.influentialpeople.hero.category.entity.Category;
import com.alek.influentialpeople.hero.category.persistence.CategoryCrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service("categoryCrudService")
public class CategoryCrudService implements CrudService<Category, String> {

    private final CategoryCrudRepository categoryRepository;

    public CategoryCrudService(CategoryCrudRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return new PageImpl<>(categoryRepository.findAll());
    }

    @Override
    public Category create(Category category) {
        if (categoryRepository.existsById(category.getName())) {
              throw new EntityExistsException(ExceptionMessages.CATEGORY_EXISTS_MESSAGE);
          }
        return categoryRepository.save(category);
    }

    @Override
    public Category findOne(String name) {
        return categoryRepository.findById(name).orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.NOT_FOUND_CATEGORY_MESSAGE));
    }

    @Override
    public void delete(String name) {
        if (categoryRepository.existsById(name)) {
            categoryRepository.deleteById(name);
            return;
        }
        throw new EntityNotFoundException(ExceptionMessages.NOT_FOUND_CATEGORY_MESSAGE);
    }

    @Override
    public Category update(String name, Category changes) {
        Optional<Category> optionalCat = categoryRepository.findById(name);
        if (optionalCat.isPresent()) {
            Category category = optionalCat.get();
            category = setChanges(category, changes);
            return categoryRepository.save(category);
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
