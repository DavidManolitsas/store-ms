package com.manolitsas.david.model;

import javax.persistence.*;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
@Table(name = "product")
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "category")
  private String category;

  @Setter
  @Column(name = "price")
  private Double price;
}
