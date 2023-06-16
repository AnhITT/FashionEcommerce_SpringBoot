package DANHHT.Fashion.service;

import DANHHT.Fashion.dto.CartDto;
import DANHHT.Fashion.model.Category;
import DANHHT.Fashion.model.Invoice;
import DANHHT.Fashion.model.Product;
import DANHHT.Fashion.model.User;
import org.aspectj.apache.bcel.classfile.Module;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface InvoiceService {
    void saveInvoice(Invoice invoice, Double total, Collection<CartDto> cart, User user);
    List<Invoice> getInvoiceByIdUser();
    public void Remove(Long id);
    List<Invoice> getAllInvoices();
    Invoice findById(Long id);
    void saveInvoice(Invoice invoice);


}
