package com.ReadIsGood.api.model.enity.order;

import com.ReadIsGood.api.model.enity.product.ProductType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class OrderLine {
    @Id
    @SequenceGenerator(
            name = "order_line_sequence",
            sequenceName = "order_line_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_line_sequence"
    )
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_header_id",referencedColumnName = "id")
    @JsonBackReference
    private OrderHeader orderHeader;
    private ProductType productType;
    private Long productId;
    private BigDecimal currentUnitPrice;
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderLine orderLine = (OrderLine) o;
        return id != null && Objects.equals(id, orderLine.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
