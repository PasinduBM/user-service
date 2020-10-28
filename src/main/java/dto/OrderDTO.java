package dto;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDTO {
    
    @NotBlank(message = "item name is required")
    private String itemName;

    @NotBlank(message = "insert a quantity")
    @Pattern(message = "quantity must be a number", regexp="^[0-9]*$")
    private String quantity;

}
