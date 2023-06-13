package DANHHT.Fashion.service.impl;

import DANHHT.Fashion.dto.CartDto;
import DANHHT.Fashion.dto.ItemDto;
import DANHHT.Fashion.repository.IProductRepository;
import DANHHT.Fashion.service.CartService;
import DANHHT.Fashion.service.ProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private static final String CART_SESSION_KEY = "cart";
    @Autowired
    private IProductRepository productRepository;
    @Override
    public CartDto getCart(@NotNull HttpSession session) {
        return Optional.ofNullable((CartDto) session.getAttribute(CART_SESSION_KEY))
                .orElseGet(() -> {
                    CartDto cart = new CartDto();
                    session.setAttribute(CART_SESSION_KEY, cart);
                    return cart;
                });
    }
    @Override
    public void updateCart(@NotNull HttpSession session, CartDto cart) {
        session.setAttribute(CART_SESSION_KEY, cart);
    }
    @Override
    public void removeCart(@NotNull HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
    }
    @Override
    public int getSumQuantity(@NotNull HttpSession session) {
        return getCart(session).getCartItems().stream()
                .mapToInt(ItemDto::getProductQuantity)
                .sum();
    }
    @Override
    public double getSumPrice(@NotNull HttpSession session) {
        return getCart(session).getCartItems().stream()
                .mapToDouble(item -> item.getProductPrice() * item.getProductQuantity())
                .sum();
    }

}
