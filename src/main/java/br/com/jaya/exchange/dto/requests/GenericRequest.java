
package br.com.jaya.exchange.dto.requests;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GenericRequest implements Serializable {

     private static final long serialVersionUID = 5911985493658203451L;

     @ApiModelProperty(name = "page", value = "Page", position = 99)
     @Min(0)
     @JsonProperty(access = Access.READ_ONLY)
     private Integer page;

     @ApiModelProperty(name = "limit", value = "Limit", position = 100)
     @Min(1)
     @Max(50)
     @JsonProperty(access = Access.READ_ONLY)
     private Integer limit;
}
