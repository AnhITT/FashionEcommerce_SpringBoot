package DANHHT.Fashion.repository;

import DANHHT.Fashion.model.Invoice;
import DANHHT.Fashion.model.InvoiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IInvoiceDetailRepository extends JpaRepository<InvoiceDetail, Long> {
    @Query("SELECT p FROM InvoiceDetail  p WHERE p.invoice.id = :id")
    List<InvoiceDetail> getInvoiceDetailByIdInvoice(Long id);
}
