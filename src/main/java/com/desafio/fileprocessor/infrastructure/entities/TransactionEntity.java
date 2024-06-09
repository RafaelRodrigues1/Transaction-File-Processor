package com.desafio.fileprocessor.infrastructure.entities;

import com.desafio.fileprocessor.domain.models.enums.MovementType;
import com.desafio.fileprocessor.domain.models.enums.OperationType;
import com.desafio.fileprocessor.domain.models.enums.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TRANSACTION")
public class TransactionEntity {

    @Id
    @Column(name = "TRANSACTION_ID")
    private String transactionId;
    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;
    @Column(name = "TRANSACTION_DATE")
    private LocalDateTime transactionDate;
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "TRANSACTION_TYPE")
    private TransactionType transactionType;
    @Column(name = "OPERATION_TYPE")
    private OperationType operationType;
    @Column(name = "MOVEMENT_TYPE")
    private MovementType movementType;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        TransactionEntity that = (TransactionEntity) o;
        return getTransactionId() != null && Objects.equals(getTransactionId(), that.getTransactionId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
