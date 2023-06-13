package DANHHT.Fashion.admin.controller;

import DANHHT.Fashion.model.Size;
import DANHHT.Fashion.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/size")
public class SizeController {
    @Autowired
    public SizeService sizeService;
    @GetMapping("")
    public  String Size(Model model){
        model.addAttribute("listsize",sizeService.getAllSize());
        return "admin/size/index";
    }
    @GetMapping("/addsize")
    public String AddSize(Model model){
        Size size = new Size();
        model.addAttribute("size",size);
        return "admin/size/addsize";
    }
    @PostMapping("/savesize")
    public String savesize(@ModelAttribute("size") Size size) {
        sizeService.saveSize(size);
        return "redirect:/size";
    }
    @GetMapping("/deletesize/{id}")
    public String Deletesize(@PathVariable(value = "id") long  id){
        this.sizeService.deleteSizeById(id);
        return "redirect:/size";
    }
    @GetMapping("/editsize/{id}")
    public String Editsize(@PathVariable(value = "id") long id, Model model) {
        Size  size = sizeService.getSizeById(id);
        model.addAttribute("size", size);
        return "admin/size/editsize";
    }
}
