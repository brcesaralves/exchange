
package br.com.jaya.exchange.dto.responses;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserResponse implements Serializable {

     private static final long serialVersionUID = 4360080465646791407L;

     @ApiModelProperty(value = "id of the user", position = 1)
     private Long id;

     @ApiModelProperty(value = "name of the user", position = 2)
     private String name;
}
