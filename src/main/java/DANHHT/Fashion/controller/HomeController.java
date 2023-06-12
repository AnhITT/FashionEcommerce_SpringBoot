package DANHHT.Fashion.controller;

import DANHHT.Fashion.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public String Home(){
        return "home/index";
    }

    @GetMapping("blog")
    public String Blog(){return "home/blog";}
    @GetMapping("contact")
    public String Contact(){return "home/contact";}

}
