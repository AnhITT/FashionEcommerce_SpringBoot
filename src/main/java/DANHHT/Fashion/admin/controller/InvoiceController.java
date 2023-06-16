package DANHHT.Fashion.admin.controller;

import DANHHT.Fashion.model.Category;
import DANHHT.Fashion.model.Invoice;
import DANHHT.Fashion.service.CategoryService;
import DANHHT.Fashion.service.InvoiceDetailService;
import DANHHT.Fashion.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private InvoiceDetailService invoiceDetailService;
    @GetMapping("")
    public String Invoice(Model model){
        model.addAttribute("listInvoice",invoiceService.getAllInvoices());
        return "admin/invoice/index";}
    @GetMapping("/confirm/{id}")
    public String confirm(@PathVariable("id") Long id) {
        var invoice = invoiceService.findById(id);
        invoice.setStatus("Confirm");
        invoiceService.saveInvoice(invoice);
        return "redirect:/invoice";
    }
    @GetMapping("/detailOrder/{id}")
    public String manageOrder(@PathVariable("id") Long id, Model model) {
        model.addAttribute("invoice", invoiceDetailService.getAllInvoiceDetail(id));
        return "admin/invoice/detailOrder";
    }
}
