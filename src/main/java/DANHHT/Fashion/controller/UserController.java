package DANHHT.Fashion.controller;


import DANHHT.Fashion.dto.CartDto;
import DANHHT.Fashion.model.Invoice;
import DANHHT.Fashion.model.User;
import DANHHT.Fashion.repository.IInvoiceDetailRepository;
import DANHHT.Fashion.service.InvoiceDetailService;
import DANHHT.Fashion.service.InvoiceService;
import DANHHT.Fashion.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private InvoiceDetailService invoiceDetailService;
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                model.addAttribute(error.getField() + "_error",
                        error.getDefaultMessage());
            }
            return "user/register";
        }
        user.setPassword(new
                BCryptPasswordEncoder().encode(user.getPassword()));
        userService.save(user);
        return "home/blog";
    }
    @GetMapping("/manageInfo")
    public String manageInfo(Model model) {
        model.addAttribute("user", userService.getUser());
        return "user/manageInfo";
    }
    @PostMapping("/saveInfo")
    public String saveInfo(@Valid @ModelAttribute("user") User user){
        userService.saveInfo(user);
        return "redirect:/manageInfo";
    }
    @GetMapping("/manageOrder")
    public String manageOrder(Model model) {
        model.addAttribute("invoice", invoiceService.getInvoiceByIdUser());
        return "user/manageOrder";
    }
    @GetMapping("/detailOrder/{id}")
    public String manageOrder(@PathVariable("id") Long id, Model model) {
        model.addAttribute("invoice", invoiceDetailService.getAllInvoiceDetail(id));
        return "user/detailOrder";
    }
}
