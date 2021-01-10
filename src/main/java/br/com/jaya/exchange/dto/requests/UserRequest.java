
package br.com.jaya.exchange.dto.requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserRequest extends GenericRequest {

     private static final long serialVersionUID = 5496470327647793627L;

     @ApiModelProperty(name = "name", value = "Username", position = 1)
     private String name;

}
