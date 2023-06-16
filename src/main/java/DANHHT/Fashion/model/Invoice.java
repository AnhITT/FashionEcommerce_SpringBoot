package DANHHT.Fashion.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "invoice")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String nameUser;
    @Column(name = "email")
    private String emailUser;
    @Column(name = "date")
    private Date invoiceDate = new Date();
    @Column(name = "address")
    private String addressUser;
    @Column(name = "phone")
    private String phoneUser;
    @Column(name = "total")
    private double totalInvoice;
    @Column(name = "note")
    private String note;
    @Column(name = "typePayment")
    private String typePayment;
    @Column(name = "typeShipping")
    private String typeShipping;
    @Column(name = "status")
    private String status;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User users;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    private List<InvoiceDetail> invoiceDetails;
}
