package DANHHT.Fashion.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idProduct;
    @Column(name = "name")
    private String nameProduct;
    @Column(name = "price")
    private double priceProduct;
    @Column(name = "img")
    private String imgProduct;
    @Column(name = "imgCover")
    private String imgCoverProduct;
    @Column(name = "description", length = 10000)
    private String desProduct;
    @Column(name = "view")
    private int viewProduct;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<InvoiceDetail> itemInvoices = new ArrayList<>();
}
