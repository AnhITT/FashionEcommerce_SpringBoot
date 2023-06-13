package DANHHT.Fashion.admin.controller;

import DANHHT.Fashion.model.Category;
import DANHHT.Fashion.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("")
    public String Category(Model model){
        model.addAttribute("listcategory",categoryService.getAllCategory());
        return "admin/category/index";}
    @GetMapping("/addcategory")
    public String AddCategory(Model model){
        Category category = new Category();
        model.addAttribute("category",category);
        return "admin/category/addcategory";
    }
    @PostMapping("/savecategory")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.saveCategory(category);
        return "redirect:/category";
    }
    @GetMapping("/deletecategory/{id}")
    public  String Deletecategory(@PathVariable(value = "id") long  id){
        this.categoryService.deleteCategoryById(id);
        return "redirect:/category";
    }
    @GetMapping("/editcategory/{id}")
    public String editCategory(@PathVariable(value = "id") long id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "admin/category/editcategory";
    }

}
