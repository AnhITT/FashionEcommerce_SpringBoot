package DANHHT.Fashion.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "size")
public class Size {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "size")
    private Set<ProductSize> productSizes;
}
