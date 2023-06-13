package DANHHT.Fashion.controller;

import DANHHT.Fashion.model.Product;
import DANHHT.Fashion.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class HomeController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public String Home(Model model){
        model.addAttribute("listProducts", productService.top10Product());
        model.addAttribute("top1Product", productService.top1Product());
        return "home/index";
    }

    @GetMapping("blog")
    public String Blog(){return "home/blog";}
    @GetMapping("contact")
    public String Contact(){return "home/contact";}

}
