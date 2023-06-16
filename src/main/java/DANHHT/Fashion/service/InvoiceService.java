package DANHHT.Fashion.service;

import DANHHT.Fashion.dto.CartDto;
import DANHHT.Fashion.model.Invoice;
import DANHHT.Fashion.model.User;

import java.util.Collection;
import java.util.List;

public interface InvoiceService {
    void saveInvoice(Invoice invoice, Double total, Collection<CartDto> cart, User user);
    List<Invoice> getInvoiceByIdUser();
    public void Remove(Long id);
}
