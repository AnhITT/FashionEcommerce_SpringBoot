package DANHHT.Fashion.controller;

import DANHHT.Fashion.dto.CartDto;
import DANHHT.Fashion.model.Product;
import DANHHT.Fashion.service.CartService;
import DANHHT.Fashion.service.ProductService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CartController {
    private CartService cartService;

    @GetMapping
    public String showCart(HttpSession session,
                           @NotNull Model model) {
        model.addAttribute("cart", cartService.getCart(session));
        model.addAttribute("totalPrice", cartService.getSumPrice(session));
        model.addAttribute("totalQuantity", cartService.getSumQuantity(session));
        return "home/cart";
    }

    @GetMapping("/removeFromCart/{id}")
    public String removeFromCart(HttpSession session,
                                 @PathVariable Long id) {
        var cart = cartService.getCart(session);
        cart.removeItems(id);
        return "redirect:/cart";
    }

    @GetMapping("/updateCart/{id}/{quantity}")
    public String updateCart(HttpSession session,
                             @PathVariable Long id,
                             @PathVariable int quantity) {
        var cart = cartService.getCart(session);
        cart.updateItems(id, quantity);
        return "home/cart";
    }

    @GetMapping("/clearCart")
    public String clearCart(HttpSession session) {
        cartService.removeCart(session);
        return "redirect:/cart";
    }
}
