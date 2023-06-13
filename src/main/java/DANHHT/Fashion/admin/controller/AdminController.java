package DANHHT.Fashion.admin.controller;

import DANHHT.Fashion.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public String Admin(){
        return "admin/home/index";
    }
    @GetMapping("/product")
    public String Product(){
        return "admin/home/product";
    }
    @GetMapping("/test")
    public String Test(){
        return "admin/home/test";
    }
    @GetMapping("/login")
    public String Login(){
        return "admin/login/login";
    }
    @GetMapping("/addproduct")
    public String Add(){
        return "admin/home/addproduct";
    }

}
