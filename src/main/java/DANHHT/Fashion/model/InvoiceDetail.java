package DANHHT.Fashion.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "invoice_detail")
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String nameProduct;
    @Column(name = "address")
    private double priceProduct;
    @Column(name = "phone")
    private int quantityProduct;
    @Column(name = "total")
    private double totalPrice;
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}
