package DANHHT.Fashion.dto;

import DANHHT.Fashion.model.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Setter
@Getter
public class CartDto {
    private Long productId;
    private String productName;
    private String productImg;
    private double productPrice;
    private int productQuantity;
}
