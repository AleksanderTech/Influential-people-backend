package com.alek.influentialpeople.user.controller;

import com.alek.influentialpeople.common.abstraction.SearchService;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.model.UserResponse;
import com.alek.influentialpeople.user.model.UserSearch;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

import static com.alek.influentialpeople.common.ConvertersFactory.ConverterType.USER_TO_USER_RESPONSE;
import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;

@RestController
@RequestMapping("/user/search")
@PreAuthorize("hasAnyRole('USER','ADMIN')")
public class SearchUserController {

    private final SearchService<User, UserSearch> searchService;
    private TwoWayConverter<User, UserResponse> userResponseConverter = getConverter(USER_TO_USER_RESPONSE);

    public SearchUserController(SearchService<User, UserSearch> searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<UserResponse>> getAll(@RequestParam(value = "paging", defaultValue = "true") Boolean paging,
                                                       @RequestParam(value = "username", required = false) String username,
                                                       @RequestParam(value = "email", required = false) String email,
                                                       @RequestParam(value = "sort", required = false) String sorting,
                                                       Pageable pageRequest) {

        UserSearch userSearch = new UserSearch(username,email,sorting,pageRequest);
        if (paging) {
            return ResponseEntity.status(HttpStatus.OK).body(searchService.findPaged(userSearch).map(user -> userResponseConverter.convert(user)));
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(searchService.findList(userSearch).stream().map(user -> userResponseConverter.convert(user)).collect(Collectors.toList()));
        }
    }
}