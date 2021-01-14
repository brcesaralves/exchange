
package br.com.jaya.exchange.dto.requests;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class TransactionRequest extends GenericRequest {

     private static final long serialVersionUID = -5707546571870280683L;

     @ApiModelProperty(name = "userId", value = "userId", position = 1)
     private Long userId;
}
