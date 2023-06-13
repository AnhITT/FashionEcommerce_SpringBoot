package DANHHT.Fashion.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private Long productId;
    private String productName;
    private Double productPrice;
    private int productQuantity;
}
