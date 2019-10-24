package com.alek.influentialpeople.user.controller;

import com.alek.influentialpeople.user.model.UserResponse;
import com.alek.influentialpeople.user.role.entity.Role;
import com.alek.influentialpeople.user.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Sets;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.ArgumentMatchers.any;
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
        mockMvc = MockMvcBuilders.standaloneSetup(userController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void findAll_properRequest_shouldReturnUsersAndStatus200() throws Exception {

        UserResponse user1 = UserResponse.builder().username("user1").roles(Sets.newHashSet(Role.Roles.ROLE_USER.name())).build();
        UserResponse user2 = UserResponse.builder().username("user2").roles(Sets.newHashSet(Role.Roles.ROLE_USER.name())).build();
        when(userService.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(Lists.list(user1, user2)));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[*].username", hasItems(user1.getUsername() ,user2.getUsername())))
                .andExpect(jsonPath("$.content[*].roles[*]",hasItems(Role.Roles.ROLE_USER.name(),Role.Roles.ROLE_USER.name())));

    }
}