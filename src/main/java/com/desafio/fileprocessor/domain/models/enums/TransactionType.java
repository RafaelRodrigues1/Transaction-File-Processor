package com.desafio.fileprocessor.domain.models.enums;

public enum TransactionType {

    CASH_DEPOSIT(OperationType.DEPOSIT),
    CHECK_DEPOSIT(OperationType.DEPOSIT),
    DIRECT_DEPOSIT(OperationType.DEPOSIT),
    ATM_WITHDRAWAL(OperationType.WITHDRAWAL),
    OVER_COUNTER_WITHDRAWAL(OperationType.WITHDRAWAL),
    BILL_PAYMENT(OperationType.PAYMENT),
    DEBIT_PAYMENT(OperationType.PAYMENT),
    AUTOMATIC_DEBIT(OperationType.PAYMENT),
    PERSONAL_LOAN(OperationType.LOAN),
    FINANCING(OperationType.LOAN),
    FINANCIAL_INVESTMENT(OperationType.INVESTMENT),
    INVESTMENT_REDEMPTION(OperationType.REDEMPTION),
    DEBIT_CARD(OperationType.CARD),
    CREDIT_CARD(OperationType.CARD);

    private OperationType operationType;

    TransactionType(OperationType operationType) {
        this.operationType = operationType;
    }

    public OperationType getOperationType() {
        return operationType;
    }
}
