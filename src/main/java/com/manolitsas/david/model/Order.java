package com.manolitsas.david.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(foreignKey = @ForeignKey(name = "customer_id"))
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Customer customer;

  @JsonManagedReference
  @OneToMany(
      mappedBy = "order",
      targetEntity = OrderLine.class,
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER)
  private List<OrderLine> orderLines;

  @Column(name = "status")
  private String status;

  @Column(name = "order_date")
  private LocalDate orderDate;

  @Column(name = "delivery_date")
  private LocalDate deliveryDate;

  @Column(name = "total")
  private Double total;
}
