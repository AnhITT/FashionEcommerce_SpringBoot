package DANHHT.Fashion.controller;

import DANHHT.Fashion.model.Category;
import DANHHT.Fashion.model.Product;
import DANHHT.Fashion.service.CartService;
import DANHHT.Fashion.service.CategoryService;
import DANHHT.Fashion.service.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public String Shop(Model model)
    {
        return findPaginated(1, model);
    }
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 9;

        Page<Product> page = productService.findPaginated(pageNo, pageSize);
        List<Product> listProducts = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listProducts", listProducts);
        return "home/shop";
    }
    @GetMapping("/category/{name}")
    public String listProductByCategory(@PathVariable("name") String name, Model model){
        if(name.equals("tops"))
        {
            List<Product> list = new ArrayList<>();
            List<Category> categoryList = new ArrayList<>();
            String[] categoryNames = {"T-Shirt", "Polo", "Sweater", "Blazer", "Jacket"};
            for (String temp : categoryNames) {
                Optional<Category> category = categoryService.getCategoryByName(temp);
                if (category.isPresent()) {
                    categoryList.add(category.get());
                    List<Product> productList = productService.getProductByCategory(category.get().getId());
                    list.addAll(productList);
                }
            }
            model.addAttribute("listProducts", list);
            return "home/productCategory";
        }
        else if(name.equals("bottoms"))
        {
            List<Product> list = new ArrayList<>();
            List<Category> categoryList = new ArrayList<>();
            String[] categoryNames = {"Jeans", "Trousers", "Underpants", "Pants", "Tights"};
            for (String temp : categoryNames) {
                Optional<Category> category = categoryService.getCategoryByName(temp);
                if (category.isPresent()) {
                    categoryList.add(category.get());
                    List<Product> productList = productService.getProductByCategory(category.get().getId());
                    list.addAll(productList);
                }
            }
            model.addAttribute("listProducts", list);
            return "home/productCategory";
        }
        else if(name.equals("accessories"))
        {
            List<Product> list = new ArrayList<>();
            List<Category> categoryList = new ArrayList<>();
            String[] categoryNames = {"Baseball cap", "Shoes", "Sandal", "Bag", "Glasses"};
            for (String temp : categoryNames) {
                Optional<Category> category = categoryService.getCategoryByName(temp);
                if (category.isPresent()) {
                    categoryList.add(category.get());
                    List<Product> productList = productService.getProductByCategory(category.get().getId());
                    list.addAll(productList);
                }
            }
            model.addAttribute("listProducts", list);
            return "home/productCategory";
        }
        else
        {
            Optional<Category> category = categoryService.getCategoryByName(name);
            List<Product> productList = productService.getProductByCategory(category.get().getId());
            model.addAttribute("listProducts", productList);
            return "home/productCategory";
        }
    }

    @GetMapping("/search")
    public String searchBook( Model model, @RequestParam String name)
    {
        model.addAttribute("listProducts", productService.searchProduct(name));
        return "home/productCategory";
    }

    @GetMapping("productDetail/{id}")
    public String productDetail(@PathVariable("id") Long id, Model model){
        var product = productService.getProductById(id);
        model.addAttribute("productItem", product.orElseThrow(() -> new IllegalArgumentException("Product not found")));
        productService.UpdateView(id);
        return "product/detail";
    }

}
