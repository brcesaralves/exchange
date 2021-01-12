
package br.com.jaya.exchange.exchange.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.common.collect.Lists;

import br.com.jaya.exchange.entities.User;
import br.com.jaya.exchange.exception.ExchangeNotFoundException;
import br.com.jaya.exchange.repositories.UserRepository;
import br.com.jaya.exchange.services.UserService;
import br.com.jaya.exchange.util.Pageable;

@RunWith(SpringRunner.class)
public class UserServiceTest {

     @InjectMocks
     private UserService userService;

     @Mock
     private UserRepository userRepository;

     private User user;

     private Optional<User> userOptional;

     private List<User> listUsers;

     private Page<User> userPage;

     private Pageable pageable;

     private final Integer PAGE = 0;

     private final Integer LIMIT = 10;

     private final Integer ZERO = 0;

     @Before
     public void before() {

          user = new User();
          user.setId(RandomUtils.nextLong(0, 999999));
          user.setName(RandomStringUtils.randomAlphanumeric(100));

          userOptional = Optional.of(user);

          listUsers = Lists.newArrayList();
          listUsers.add(user);

          pageable = Pageable.setPageable(PAGE, LIMIT);

          userPage = new PageImpl<User>(listUsers, pageable, listUsers.size());
     }

     @SuppressWarnings("unchecked")
     @Test
     public void testListUsers() {

          when(userRepository.findAll(any(Example.class), any(Pageable.class))).thenReturn(userPage);

          Page<User> response = userService.list(user, pageable);

          assertNotNull(response);
          assertTrue(response.getContent().size() > ZERO);
          assertEquals(user.getId(), response.getContent().get(ZERO).getId());
          assertEquals(user.getName(), response.getContent().get(ZERO).getName());
     }

     @SuppressWarnings("unchecked")
     @Test
     public void testListUsersEmpty() {

          userPage = new PageImpl<User>(Lists.newArrayList(), pageable, ZERO);

          when(userRepository.findAll(any(Example.class), any(Pageable.class))).thenReturn(userPage);

          Page<User> response = userService.list(user, pageable);

          assertNotNull(response);
          assertEquals(response.getContent().size(), 0);
     }

     @Test
     public void testFindUser() {

          when(userRepository.findById(anyLong())).thenReturn(userOptional);

          User response = userService.find(user.getId());
          assertNotNull(response);
          assertEquals(user.getId(), response.getId());
          assertEquals(user.getName(), response.getName());
     }

     @Test(expected = ExchangeNotFoundException.class)
     public void testFindUserNotFound() {

          when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
          userService.find(user.getId());
     }

     @Test
     public void testSave() {

          when(userRepository.save(any(User.class))).thenReturn(user);

          User response = new User();
          response.setName(user.getName());

          response = userService.save(response);
          assertNotNull(response);
          assertEquals(user.getId(), response.getId());
          assertEquals(user.getName(), response.getName());
     }

     @Test
     public void testUpdate() {

          user.setName(RandomStringUtils.randomAlphanumeric(100));

          when(userRepository.findById(anyLong())).thenReturn(userOptional);
          when(userRepository.save(any(User.class))).thenReturn(user);

          User response = new User();
          response = userService.update(user);
          assertNotNull(response);
          assertEquals(userOptional.get().getId(), response.getId());
          assertEquals(user.getName(), response.getName());
     }

     @Test(expected = ExchangeNotFoundException.class)
     public void testUpdateUserNotFound() {

          when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
          userService.update(user);
     }

     @Test
     public void testDelete() {

          when(userRepository.findById(anyLong())).thenReturn(userOptional);

          userService.delete(user.getId());

          verify(userRepository, times(1)).deleteById(anyLong());
     }

     @Test(expected = ExchangeNotFoundException.class)
     public void testDeleteUserNotFound() {

          when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
          userService.delete(user.getId());
     }
}
