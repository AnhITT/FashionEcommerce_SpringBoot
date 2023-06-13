package DANHHT.Fashion.dto;

import DANHHT.Fashion.model.Product;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Data
public class CartDto {
    private List<ItemDto> cartItems = new ArrayList<>();

    public void addItems(ItemDto item) {
        boolean isExist = cartItems.stream()
                .filter(i -> Objects.equals(i.getProductId(), item.getProductId()))
                .findFirst()
                .map(i -> {
                    i.setProductQuantity(i.getProductQuantity() + item.getProductQuantity());
                    return true;
                })
                .orElse(false);

        if (!isExist) {
            cartItems.add(item);
        }
    }

    public void removeItems(Long productId) {
        cartItems.removeIf(item -> Objects.equals(item.getProductId(), productId));
    }

    public void updateItems(Long productId, int quantity) {
        cartItems.stream()
                .filter(item -> Objects.equals(item.getProductId(), productId))
                .forEach(item -> item.setProductQuantity(quantity));
    }
}
