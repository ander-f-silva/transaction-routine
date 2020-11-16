package br.com.pm.account.infrastructure.jpa.entities;

import javax.persistence.*;

@Entity(name = "accounts")
public class AccountEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "document_number", updatable = false, nullable = false)
  private String documentNumber;

  public AccountEntity() {}

  public AccountEntity(Long id, String documentNumber) {
    this.id = id;
    this.documentNumber = documentNumber;
  }

  public AccountEntity(String documentNumber) {
    this.documentNumber = documentNumber;
  }

  public Long getId() {
    return id;
  }

  public String getDocumentNumber() {
    return documentNumber;
  }
}
