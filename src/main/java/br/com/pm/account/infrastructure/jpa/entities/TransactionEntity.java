package br.com.pm.account.infrastructure.jpa.entities;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "transactions")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private AccountEntity account;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "operation_type_id")
    private OperationTypeEntity operationType;

    private Double amount;

    @CreationTimestamp
    @Column(name = "event_date")
    private LocalDateTime eventDate;

    public TransactionEntity() {
    }

    public Long getId() {
        return id;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public OperationTypeEntity getOperationType() {
        return operationType;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }
}
