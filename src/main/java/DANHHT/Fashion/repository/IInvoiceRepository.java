package DANHHT.Fashion.repository;

import DANHHT.Fashion.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IInvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("SELECT p FROM Invoice  p WHERE p.users.id = :id")
    List<Invoice> getInvoicesByIdUser(Long id);
    @Query("SELECT p FROM Invoice p where  p.id =:id")
    Invoice getInvoicesById(Long id);
}
