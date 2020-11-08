package br.com.pm.account.infrastructure.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "operations_types")
public class OperationTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String description;

    public OperationTypeEntity() {
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
