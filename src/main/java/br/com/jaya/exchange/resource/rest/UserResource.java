
package br.com.jaya.exchange.resource.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jaya.exchange.entities.User;
import br.com.jaya.exchange.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(produces = MediaType.APPLICATION_JSON_VALUE, description = "descrition", tags = { "Users" })
@RestController
@RequestMapping(path = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResource {

     private final UserService userService;

     public UserResource(UserService userService) {

          this.userService = userService;
     }

     @Validated
     @ApiOperation(value = "list users", notes = "list users notes", response = Page.class, nickname = "listUsers")
     @GetMapping()
     public Page<User> listUsers(
               @ModelAttribute(value = "pageable") Pageable pageable) {

          return userService.list(pageable);
     }

     @Validated
     @ApiOperation(value = "get users", notes = "get users notes", response = User.class, nickname = "getUser")
     @GetMapping("/{id}")
     public User getUser(
               @ApiParam(value = "configuracao_controle_cartao_resource_id_consultar", required = true)
               @PathVariable("id") Long id) {

          return userService.find(id);
     }
}
