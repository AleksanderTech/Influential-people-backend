package com.alek.influentialpeople.user.controller;

import com.alek.influentialpeople.common.ImageManager;
import com.alek.influentialpeople.common.ImageType;
import com.alek.influentialpeople.common.Properties;
import com.alek.influentialpeople.common.abstraction.SearchService;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.model.UserResponse;
import com.alek.influentialpeople.user.model.UserSearch;
import com.alek.influentialpeople.user.service.UserResponseConverter;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;


@RestController
@RequestMapping("/user/search")
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class SearchUserController {

    private TwoWayConverter<User, UserResponse> userConverter = new UserResponseConverter();

    private final Properties properties;
    private final SearchService<User, UserSearch> searchService;

    public SearchUserController(Properties properties, SearchService<User, UserSearch> searchService) {
        this.properties = properties;
        this.searchService = searchService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<UserResponse>> getAll(@RequestParam(value = "paging", defaultValue = "true") Boolean paging,
                                                         @RequestParam(value = "username", required = false) String username,
                                                         @RequestParam(value = "email", required = false) String email,
                                                         @RequestParam(value = "sort", required = false) String sorting,
                                                         Pageable pageRequest) {

        UserSearch userSearch = new UserSearch(username, email, sorting, pageRequest);
        if (paging) {
            return ResponseEntity.status(HttpStatus.OK).body(searchService.findPaged(userSearch).map(user -> {
                UserResponse response = userConverter.convert(user);
                response.setAvatarImageUrl(ImageManager.createUrl(user.getAvatarImagePath(), properties.getConfig("server.url"), ImageType.USER, user.getUsername()));
                return response;
            }));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(searchService.findList(userSearch).stream().map(user -> {
                UserResponse response = userConverter.convert(user);
                response.setAvatarImageUrl(ImageManager.createUrl(user.getAvatarImagePath(), properties.getConfig("server.url"), ImageType.USER, user.getUsername()));
                return response;
            }).collect(Collectors.toList()));
        }
    }
}