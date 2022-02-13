package sasa.jovanovic.musicshop.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sku", unique = true, nullable = false)
    private String sku;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "unit_price", nullable = false)
    @Min(value = 0)
    private BigDecimal unitPrice;

    @Column(name = "image_url", nullable = false)
    @NotNull
    private String imageUrl;

    @Column(name = "active", nullable = false)
    @NotNull
    private boolean active;

    @Column(name = "units_in_stock", nullable = false)
    @Min(value = 0)
    private int unitsInStock;

    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    private Date lastUpdated;

    @ManyToOne()
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private ProductCategory category;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }
}
