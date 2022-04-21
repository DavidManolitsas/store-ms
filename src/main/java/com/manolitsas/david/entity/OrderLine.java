package com.manolitsas.david.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "order_line")
public class OrderLine {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name = "order_id")
  private Order order;

  @ManyToOne
  @JoinColumn(foreignKey = @ForeignKey(name = "product_id"))
  private Product product;

  @Column(name = "quantity", nullable = false)
  private Integer quantity;

  public double getLineTotal() {
    return product.getPrice() * quantity;
  }
}
