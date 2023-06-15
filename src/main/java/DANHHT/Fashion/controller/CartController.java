package DANHHT.Fashion.controller;

import DANHHT.Fashion.dto.CartDto;
import DANHHT.Fashion.model.Product;
import DANHHT.Fashion.service.CartService;
import DANHHT.Fashion.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ProductService productService;

    @Autowired
    private CartService cartService;

    @GetMapping("view")
    public String ViewCart(Model model){
        model.addAttribute("all_items_in_shoppingcart", cartService.GetAllItem());
        model.addAttribute("total_amount", cartService.GetAmount());
        return "home/cart";
    }

    @GetMapping("add/{id}/{returnUrl}")
    public String addItem(@PathVariable("id") Long id, @PathVariable("returnUrl") String returnUrl) {
        Product product = productService.GetProductById(id);
        if (product != null) {
            CartDto item = new CartDto();
            item.setProductId(product.getIdProduct());
            item.setProductName(product.getNameProduct());
            item.setProductImg(product.getImgProduct());
            item.setProductPrice(product.getPriceProduct());
            item.setProductQuantity(1);
            cartService.Add(item);
        }
        return "redirect:/" + returnUrl;
    }

    @GetMapping("clear")
    public String clearCart(){
        cartService.Clear();
        return "redirect:/cart/view";
    }

    @GetMapping("remove/{id}")
    public String removeItem(@PathVariable("id") Long id){
        cartService.Remove(id);
        return "redirect:/cart/view";
    }

    @PostMapping("update")
    public String updateItem(@RequestParam("productId") Long productId,
                             @RequestParam("quantity") Integer quantity) {
        cartService.Update(productId, quantity);
        return "redirect:/cart/view";
    }
}
