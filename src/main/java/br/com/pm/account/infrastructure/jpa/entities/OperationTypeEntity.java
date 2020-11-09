package br.com.pm.account.infrastructure.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "operations_types")
public class OperationTypeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String description;

  public OperationTypeEntity(Integer id, String description) {
    this.id = id;
    this.description = description;
  }

  public OperationTypeEntity() {}

  public Integer getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }
}
