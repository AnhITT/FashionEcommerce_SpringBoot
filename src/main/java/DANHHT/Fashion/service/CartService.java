package DANHHT.Fashion.service;

import DANHHT.Fashion.dto.CartDto;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;

public interface CartService {
    CartDto getCart(@NotNull HttpSession session);
    void updateCart(@NotNull HttpSession session, CartDto cart);
    void removeCart(@NotNull HttpSession session);
    int getSumQuantity(@NotNull HttpSession session);
    double getSumPrice(@NotNull HttpSession session);
}
