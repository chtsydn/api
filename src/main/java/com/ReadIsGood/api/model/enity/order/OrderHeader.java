package com.ReadIsGood.api.model.enity.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class OrderHeader {
    @Id
    @SequenceGenerator(
            name = "order_header_sequence",
            sequenceName = "order_header_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "order_header_sequence"
    )
    private Long id;
    private Long customer_id;
    @JsonFormat (pattern = "yyyy-MM-dd HH:mm:ss")
    private Date order_time;
    private BigDecimal order_amount;
    private String order_note;
    private OrderStatus order_status;
    @OneToMany(mappedBy = "orderHeader",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<OrderLine> orderLines;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderHeader orderHeader = (OrderHeader) o;
        return id != null && Objects.equals(id, orderHeader.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
