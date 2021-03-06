
package br.com.jaya.exchange.dto.persist;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserPersist implements Serializable {

     private static final long serialVersionUID = 3398679037240972476L;

     @NotNull
     @Size(max = 100)
     @ApiModelProperty(name = "name", value = "Username", position = 1)
     private String name;

}
