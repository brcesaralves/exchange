
package br.com.jaya.exchange.resource.rest;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jaya.exchange.converters.GenericConvert;
import br.com.jaya.exchange.dto.persist.UserPersist;
import br.com.jaya.exchange.dto.requests.UserRequest;
import br.com.jaya.exchange.dto.responses.UserResponse;
import br.com.jaya.exchange.dto.update.UserUpdate;
import br.com.jaya.exchange.entities.User;
import br.com.jaya.exchange.services.UserService;
import br.com.jaya.exchange.util.Pageable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(produces = MediaType.APPLICATION_JSON_VALUE, tags = { "User" })
@RestController
@RequestMapping(path = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {

     private final UserService userService;

     public UserResource(UserService userService) {

          this.userService = userService;
     }

     @GetMapping()
     @ApiOperation(value = "List users", notes = "This feature allows you to list registered users.", response = Page.class, nickname = "listUsers")
     public Page<User> list(
               @Validated @ModelAttribute(value = "request") UserRequest userRequest) {

          User user = GenericConvert.convertModelMapper(userRequest, User.class);
          Pageable pageable = Pageable.setPageable(userRequest.getPage(), userRequest.getLimit());

          return userService.list(user, pageable);
     }

     @GetMapping("/{id}")
     @ApiOperation(value = "Get users", notes = "This method allows to consult the user from the identification code (id).", response = UserResponse.class, nickname = "getUser")
     public ResponseEntity<UserResponse> get(
               @ApiParam(value = "id", required = true)
               @PathVariable("id") Long id) {

          User user = userService.find(id);
          UserResponse response = GenericConvert.convertModelMapper(user, UserResponse.class);

          return ResponseEntity.ok(response);
     }

     @PostMapping()
     @ApiOperation(value = "Registers the user", notes = "This method allows you to register the user.", response = UserResponse.class, nickname = "saveUser")
     public ResponseEntity<UserResponse> save(
               @Validated @RequestBody UserPersist userPersist) {

         User user = GenericConvert.convertModelMapper(userPersist, User.class);
         user = userService.save(user);

         UserResponse response = GenericConvert.convertModelMapper(user, UserResponse.class);

         return ResponseEntity.status(HttpStatus.CREATED).body(response);
     }

     @PutMapping("/{id}")
     @ApiOperation(value = "Update user", notes = "This method allows to update the user from the identification code (id).", response = UserResponse.class, nickname = "updateUser")
     public ResponseEntity<UserResponse> update(
               @ApiParam(value = "id", required = true)
               @PathVariable("id") Long id,
               @Validated @RequestBody UserUpdate userUpdate) {

          User user = GenericConvert.convertModelMapper(userUpdate, User.class);
          user.setId(id);

          user = userService.update(user);

          UserResponse response = GenericConvert.convertModelMapper(user, UserResponse.class);

          return ResponseEntity.ok(response);
     }

     @DeleteMapping("/{id}")
     @ApiOperation(value = "Delete user", notes = "This method allows to delete the user from the identification code (id).")
     public ResponseEntity<?> delete(
               @PathVariable("id") Long id) {

          userService.delete(id);

          return ResponseEntity.noContent().build();
     }
}
