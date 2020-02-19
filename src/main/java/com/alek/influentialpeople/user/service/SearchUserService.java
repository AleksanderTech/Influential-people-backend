package com.alek.influentialpeople.user.service;

import com.alek.influentialpeople.common.abstraction.SearchService;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.model.UserSearch;
import com.alek.influentialpeople.user.persistence.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchUserService implements SearchService<User, UserSearch> {


    private UserRepository userRepository;

    public SearchUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> findPaged(UserSearch model) {
        return userRepository.findAll(model, model.getPageRequest());
    }

    @Override
    public List<User> findList(UserSearch model) {

        return userRepository.findAll(model);
    }
}
