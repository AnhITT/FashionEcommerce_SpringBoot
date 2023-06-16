package DANHHT.Fashion.service.impl;

import DANHHT.Fashion.dto.CartDto;
import DANHHT.Fashion.model.Invoice;
import DANHHT.Fashion.model.InvoiceDetail;
import DANHHT.Fashion.model.User;
import DANHHT.Fashion.repository.*;
import DANHHT.Fashion.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private IInvoiceRepository  invoiceRepository;
    @Autowired
    private IProductRepository  productRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IInvoiceDetailRepository iInvoiceDetailRepository;
    @Override
    public void saveInvoice(Invoice invoice, Double total, Collection<CartDto> cart, User user) {
        invoice.setInvoiceDate(new Date(new Date().getTime()));
        invoice.setTotalInvoice(total);
        invoice.setStatus("Wait for confirmation");
        invoice.setUsers(userRepository.findById(user.getId()).orElseThrow());
        invoiceRepository.save(invoice);

        cart.forEach(item -> {
            var items = new InvoiceDetail();
            items.setInvoice(invoice);
            items.setProduct(productRepository.findById(item.getProductId()).orElseThrow());
            items.setQuantity(item.getProductQuantity());
            items.setTotalPrice(item.getProductPrice() * item.getProductQuantity());
            iInvoiceDetailRepository.save(items);
        });
    }

    @Override
    public List<Invoice> getInvoiceByIdUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            String username = authentication.getName();
            var id = userRepository.getUserIdByUsername(username);
            return invoiceRepository.getInvoicesByIdUser(id);
        }
        else
        {
            return null;
        }
    }

    @Override
    public void Remove(Long id) {
        invoiceRepository.deleteById(id);
    }
}
