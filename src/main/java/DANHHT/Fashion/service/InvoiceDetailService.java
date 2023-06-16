package DANHHT.Fashion.service;

import DANHHT.Fashion.model.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceDetailService{
    List<InvoiceDetail> getAllInvoiceDetail(Long id);

}
