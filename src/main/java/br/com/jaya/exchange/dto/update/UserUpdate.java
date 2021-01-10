
package br.com.jaya.exchange.dto.update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserUpdate {

     @NotNull
     @Size(max = 200)
     @ApiModelProperty(name = "name", value = "Username", position = 1)
     private String name;
}
