package DANHHT.Fashion.service;

import DANHHT.Fashion.dto.CartDto;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;

import java.util.Collection;

public interface CartService {
    void Add(CartDto newItem);
    void Remove(Long id);
    CartDto Update(Long productId, int quantity);
    void Clear();
    double GetAmount();
    int GetCount();
    Collection<CartDto> GetAllItem();
    public void checkOut();
}
