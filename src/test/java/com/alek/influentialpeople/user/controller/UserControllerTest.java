package com.alek.influentialpeople.user.controller;

import com.alek.influentialpeople.exception.controller.ExceptionController;
import com.alek.influentialpeople.user.model.UserResponse;
import com.alek.influentialpeople.user.role.entity.Role;
import com.alek.influentialpeople.user.service.UserService;
import com.google.common.collect.Sets;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).setControllerAdvice(new ExceptionController()).build();
    }

    @Test
    public void findAll_usersFound_shouldReturnUsersAndStatus200() throws Exception {

        UserResponse user1 = UserResponse.builder().username("user1").email("email1@email.com").roles(Sets.newHashSet(Role.Roles.ROLE_USER.name())).build();
        UserResponse user2 = UserResponse.builder().username("user2").email("email2@email.com").roles(Sets.newHashSet(Role.Roles.ROLE_USER.name())).build();

        when(userService.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Lists.list(user1, user2)));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[*].username", hasItems(user1.getUsername(), user2.getUsername())))
                .andExpect(jsonPath("$.content[*].email", hasItems(user1.getEmail(), user2.getEmail())))
                .andExpect(jsonPath("$.content[*].roles[*]", hasItems(Role.Roles.ROLE_USER.name(), Role.Roles.ROLE_USER.name())));

        verify(userService, Mockito.times(1)).findAll(any(Pageable.class));
    }

    @Test
    public void findAll_usersNotFound_shouldReturnStatus200() throws Exception {

        when(userService.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Lists.emptyList()));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.content", hasSize(0)));

        verify(userService, Mockito.times(1)).findAll(any(Pageable.class));
    }


    @Test
    public void findUser_userFound_shouldReturnUserAndSatus200() throws Exception {

        UserResponse user1 = UserResponse.builder().username("user1").email("email1@email.com").roles(Sets.newHashSet(Role.Roles.ROLE_USER.name())).build();

        when(userService.findUser(eq(user1.getUsername()), eq(true)))
                .thenReturn(user1);

        mockMvc.perform(get("/users/" + user1.getUsername()))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(user1.getUsername()))
                .andExpect(jsonPath("$.email").value(user1.getEmail()))
                .andExpect(jsonPath("$.roles.[*]").value(Lists.newArrayList(user1.getRoles())));
        verify(userService, Mockito.times(1)).findUser(any(String.class), any(Boolean.class));
    }

    @Test
    public void findUser_userNotFound_shouldReturnStatus404() throws Exception {

        when(userService.findUser(any(String.class), any(Boolean.class)))
                .thenThrow(UsernameNotFoundException.class);

        mockMvc.perform(get("/users/" + "notExistingUsername"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));
    }

//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
//    @RequestMapping(path = "/{username}", method = RequestMethod.GET)
//    public ResponseEntity<UserResponse> findUser(@PathVariable String username) {
//
//        return new ResponseEntity(userService.findUser(username, true), HttpStatus.OK);
//    }
//    @PreAuthorize("hasRole('ADMIN')")
//    @RequestMapping(path = "/{username}", method = RequestMethod.DELETE)
//    public ResponseEntity deleteUser(@PathVariable String username) {
//
//        userService.deleteUser(username, true);
//        return new ResponseEntity(HttpStatus.NO_CONTENT);
//    }
//
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @RequestMapping(method = RequestMethod.POST)
//    public ResponseEntity<UserResponse> createUser(@RequestBody User user) {
//
//        return new ResponseEntity(userService.createUser(user, false), HttpStatus.CREATED);
//    }
}