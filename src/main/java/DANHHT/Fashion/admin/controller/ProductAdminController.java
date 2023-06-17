package DANHHT.Fashion.admin.controller;

import DANHHT.Fashion.model.Category;
import DANHHT.Fashion.model.Product;
import DANHHT.Fashion.service.CategoryService;
import DANHHT.Fashion.service.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin/product")
public class ProductAdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("")
    public String Product(Model model){
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
        return "admin/product/product";
    }

   /* @GetMapping("/addproduct")
    public String Add(){
        return "admin/product/addproduct";
    }*/
    @GetMapping("/deleteproduct/{id}")
    public  String Deleteproduct(@PathVariable(value = "id") long  id){
        this.productService.deleteProdcutById(id);
        return "redirect:/admin/product";
    }

    @GetMapping("/viewproduct/{id}")
    public String viewProduct(@PathVariable(value = "id") long id, Model model) {
        Optional<Product> optionalProduct = this.productService.getProductById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            model.addAttribute("product", product);
            return "admin/product/viewproduct";
        } else {
            // Xử lý trường hợp không tìm thấy sản phẩm
            return "error";
        }
    }

    @GetMapping("/addproduct")
    public String Addproduct(Model model){
        Product product = new Product();
        product.setViewProduct(0);
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("product",product);
        return "admin/product/addproduct";
    }
    @PostMapping("/addproduct")
    public String Addproduct(@Valid @ModelAttribute("product") Product product,
                             @NotNull BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            model.addAttribute("categories", categoryService.getAllCategory());
            return "admin/product/addproduct";
        }
        productService.saveProduct(product);
        return "redirect:/admin/product";
    }
    @GetMapping("/edit/{id}")
    public String editProductForm(@NotNull Model model, @PathVariable Long id) {
        var product = productService.getProductById(id);
        model.addAttribute("product", product.orElseThrow(() -> new IllegalArgumentException("Product not found")));
        model.addAttribute("categories", categoryService.getAllCategory());
        return "admin/product/editproduct";
    }

    @PostMapping("/edit")
    public String editBook(@Valid @ModelAttribute("product") Product product,
                           @NotNull BindingResult bindingResult,
                           @NotNull Model model) {
        if (bindingResult.hasErrors()) {
            var errors = bindingResult.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new);
            model.addAttribute("errors", errors);
            model.addAttribute("categories", categoryService.getAllCategory());
            return "admin/product/editproduct";
        }
        productService.updateProduct(product);
        return "redirect:/admin/product";
    }
}
