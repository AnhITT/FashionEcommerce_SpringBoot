package DANHHT.Fashion.controller;


import DANHHT.Fashion.dto.CartDto;
import DANHHT.Fashion.model.Invoice;
import DANHHT.Fashion.model.User;
import DANHHT.Fashion.service.CartService;
import DANHHT.Fashion.service.InvoiceService;
import DANHHT.Fashion.service.ProductService;
import DANHHT.Fashion.service.UserService;
import DANHHT.Fashion.service.impl.PaypalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
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
    @Autowired
    PaypalService service;

    public static final String SUCCESS_URL = "checkout/checkoutSuccess";
    public static final String CANCEL_URL = "checkout/checkoutFaild";
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

    @PostMapping("/pay")
    public String payment(@ModelAttribute("invoice") Invoice invoice) {
        try {
            double totalAmouth = cartService.GetAmount();
            Payment payment = service.createPayment(totalAmouth,
                   "USD", "PAYPAL" ,
                    "NONE", "",
                    "http://localhost:8080/" + CANCEL_URL,
                    "http://localhost:8080/" + SUCCESS_URL);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    return "redirect:"+link.getHref();
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "checkout/checkoutFaild";
    }

    @GetMapping(value = SUCCESS_URL)
    public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
        try {
            Payment payment = service.executePayment(paymentId, payerId);
            System.out.println(payment.toJSON());
            if (payment.getState().equals("approved")) {
                return "checkout/checkoutSuccess";
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return "redirect:/";
    }

}
