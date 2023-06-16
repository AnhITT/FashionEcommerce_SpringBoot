package DANHHT.Fashion.admin.controller;

import DANHHT.Fashion.model.Invoice;
import DANHHT.Fashion.service.InvoiceService;
import DANHHT.Fashion.service.ProductService;
import DANHHT.Fashion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private InvoiceService invoiceService;
    @GetMapping
    public String Admin(Model model){
        model.addAttribute("countProduct", productService.getAllProducts().stream().count());
        model.addAttribute("countAccount", userService.getAllUsers().stream().count());
        List<Invoice> invoices = invoiceService.getAllInvoices();
        model.addAttribute("countInvoice", invoices.stream().count());
        double total = invoices.stream()
                .mapToDouble(Invoice::getTotalInvoice)
                .sum();
        model.addAttribute("sumInvoice", total);
        return "admin/home/index";
    }

    @GetMapping("/test")
    public String Test(){
        return "admin/home/test";
    }
    @GetMapping("/login")
    public String Login(){
        return "admin/login/login";
    }


}
