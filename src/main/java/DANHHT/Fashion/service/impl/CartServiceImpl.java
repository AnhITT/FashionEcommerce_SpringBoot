package DANHHT.Fashion.service.impl;

import DANHHT.Fashion.dto.CartDto;
import DANHHT.Fashion.service.CartService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    Map<Long, CartDto> shoppingCart = new HashMap<>();
    @Override
    public void Add(CartDto newItem) {
        CartDto cartItem = shoppingCart.get(newItem.getProductId());
        if(cartItem == null){
            shoppingCart.put(newItem.getProductId(), newItem);
        }
        else {
            cartItem.setProductQuantity(cartItem.getProductQuantity()+1);
        }
    }

    @Override
    public void Remove(Long id) {
        shoppingCart.remove(id);
    }

    @Override
    public CartDto Update(Long productId, int quantity) {
        CartDto cartItem = shoppingCart.get(productId);
        cartItem.setProductQuantity(quantity);
        return cartItem;
    }

    @Override
    public void Clear() {
        shoppingCart.clear();
    }

    @Override
    public double GetAmount() {
        return shoppingCart.values().stream()
                .mapToDouble(item -> item.getProductQuantity()* item.getProductPrice()).sum();
    }

    @Override
    public int GetCount() {
        return shoppingCart.values().size();
    }

    @Override
    public Collection<CartDto> GetAllItem() {
        return shoppingCart.values();
    }
}
