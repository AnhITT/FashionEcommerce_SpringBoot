package DANHHT.Fashion.controller;

import DANHHT.Fashion.dto.CartDto;
import DANHHT.Fashion.model.Invoice;
import DANHHT.Fashion.model.User;
import DANHHT.Fashion.service.CartService;
import DANHHT.Fashion.service.InvoiceService;
import DANHHT.Fashion.service.ProductService;
import DANHHT.Fashion.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
@RequestMapping("/checkout")
public class CheckOutController {
    @Autowired
    ProductService productService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;
    @GetMapping("")
    public String checkOut(Model model){
        model.addAttribute("all_items_in_shoppingcart", cartService.GetAllItem());
        model.addAttribute("total_amount", cartService.GetAmount());
        User user = userService.getUser();
        Invoice invoice = new Invoice();
        invoice.setNameUser(user.getFullName());
        invoice.setEmailUser(user.getEmail());
        invoice.setPhoneUser(user.getPhoneNumber());
        invoice.setAddressUser(user.getAddress());
        model.addAttribute("invoice", invoice);
        return "checkout/checkout";
    }

    @PostMapping("saveInvoice")
    public String saveInvoice(@Valid @ModelAttribute("invoice") Invoice invoice){
        double totalAmouth = cartService.GetAmount();
        Collection<CartDto> cart = cartService.GetAllItem();
        User user = userService.getUser();
        invoiceService.saveInvoice(invoice, totalAmouth, cart, user);
        cartService.Clear();
        return "checkout/checkoutSuccess";
    }
    @GetMapping("remove/{id}")
    public String removeItem(@PathVariable("id") Long id){
        invoiceService.Remove(id);
        return "redirect:/manageOrder";
    }
}
