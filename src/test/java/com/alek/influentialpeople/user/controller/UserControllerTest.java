package com.alek.influentialpeople.user.controller;

import com.alek.influentialpeople.TestUtils;
import com.alek.influentialpeople.common.ConvertersFactory;
import com.alek.influentialpeople.common.TwoWayConverter;
import com.alek.influentialpeople.exception.ExceptionMessages;
import com.alek.influentialpeople.exception.controller.ExceptionController;
import com.alek.influentialpeople.exception.exceptions.EntityExistsException;
import com.alek.influentialpeople.exception.exceptions.StateConflictException;
import com.alek.influentialpeople.user.entity.User;
import com.alek.influentialpeople.user.model.UserAccount;
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

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.alek.influentialpeople.common.ConvertersFactory.getConverter;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItems;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private TwoWayConverter<UserAccount, User> converterA = getConverter(ConvertersFactory.ConverterType.USER_ACCOUNT_TO_USER);
    private MockMvc mockMvc;
    private User user1;
    private User user2;

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @Before
    public void setUp() {
        user1 = User.builder().username("user1").email("email1@email.com").roles(Sets.newHashSet(new Role(Role.Roles.ROLE_USER))).build();
        user2 = User.builder().username("user2").email("email2@email.com").roles(Sets.newHashSet(new Role(Role.Roles.ROLE_USER))).build();
        mockMvc = MockMvcBuilders.standaloneSetup(userController).setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).setControllerAdvice(new ExceptionController()).build();
    }

    @Test
    public void findAll_usersFound_shouldReturnUsersAndStatus200() throws Exception {

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

        when(userService.findUser(eq(user1.getUsername())))
                .thenReturn(user1);

        mockMvc.perform(get("/users/" + user1.getUsername()))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(user1.getUsername()))
                .andExpect(jsonPath("$.email").value(user1.getEmail()))
                .andExpect(jsonPath("$.roles.[*]").value(user1.getRoles().stream().map(role->role.getName()).collect(Collectors.toList())));
        verify(userService, Mockito.times(1)).findUser(any(String.class));
    }

    @Test
    public void findUser_userNotFound_shouldReturnStatus404() throws Exception {

        when(userService.findUser(any(String.class)))
                .thenThrow(UsernameNotFoundException.class);

        mockMvc.perform(get("/users/" + "notExistingUsername"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.message").value(ExceptionMessages.NOT_FOUND_USER_MESSAGE));
        verify(userService, Mockito.times(1)).findUser(any(String.class));
    }

    @Test
    public void createUser_userDoesNotExist_shouldReturnStatus201() throws Exception {

        when(userService.createUser(any(User.class)))
                .thenReturn(user1);
        mockMvc.perform(post("/users").contentType(APPLICATION_JSON_UTF8)
                .content(TestUtils.stringify(converterA.convertBack(user1))))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.username").value(user1.getUsername()))
                .andExpect(jsonPath("$.email").value(user1.getEmail()))
                .andExpect(jsonPath("$.roles.[*]").value(user1.getRoles().stream().map(role->role.getName()).collect(Collectors.toList())));

        verify(userService, Mockito.times(1)).createUser(any(User.class));
    }

    @Test
    public void createUser_userAlreadyExists_shouldReturnStatus409() throws Exception {

        when(userService.createUser(any(User.class)))
                .thenThrow(new EntityExistsException(ExceptionMessages.ENTITY_ALREADY_EXIST_MESSAGE));
        mockMvc.perform(post("/users").contentType(APPLICATION_JSON_UTF8)
                .content(TestUtils.stringify(converterA.convertBack(user1))))
                .andExpect(status().isConflict())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.message").value(ExceptionMessages.USER_ALREADY_EXIST_MESSAGE));

        verify(userService, Mockito.times(1)).createUser(any(User.class));
    }

    @Test
    public void deleteUser_deletesAnotherUser_shouldReturnStatus204() throws Exception {

        mockMvc.perform(delete("/users/" + user1.getUsername()).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent());

        verify(userService, Mockito.times(1)).deleteUser(any(String.class));
    }

    @Test
    public void deleteUser_userDeletesHimself_shouldReturnStatus409() throws Exception {

        doThrow(StateConflictException.class).when(userService).deleteUser(any(String.class));

        mockMvc.perform(delete("/users/" + user1.getUsername()).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value(ExceptionMessages.STATE_CONFLICT_MESSAGE));

        verify(userService, Mockito.times(1)).deleteUser(any(String.class));
    }
}