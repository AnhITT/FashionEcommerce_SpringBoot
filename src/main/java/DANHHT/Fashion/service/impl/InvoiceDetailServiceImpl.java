package DANHHT.Fashion.service.impl;

import DANHHT.Fashion.model.InvoiceDetail;
import DANHHT.Fashion.repository.IInvoiceDetailRepository;
import DANHHT.Fashion.service.InvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceDetailServiceImpl implements InvoiceDetailService {
    @Autowired
    private IInvoiceDetailRepository invoiceDetailRepository;
    @Override
    public List<InvoiceDetail> getAllInvoiceDetail(Long id) {
        return invoiceDetailRepository.getInvoiceDetailByIdInvoice(id);
    }
}
