package DANHHT.Fashion.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ToString.Exclude
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<InvoiceDetail> itemInvoices = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Product book = (Product) o;
        return false;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
