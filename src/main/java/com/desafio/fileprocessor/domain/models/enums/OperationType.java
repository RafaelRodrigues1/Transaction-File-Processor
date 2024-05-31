package com.desafio.fileprocessor.domain.models.enums;

public enum OperationType {

    DEPOSIT(MovementType.IN),
    WITHDRAWAL(MovementType.OUT),
    PAYMENT(MovementType.OUT),
    LOAN(MovementType.IN),
    INVESTMENT(MovementType.OUT),
    REDEMPTION(MovementType.IN),
    CARD(MovementType.OUT);

    private final MovementType movementType;

    OperationType(MovementType movementType) {
        this.movementType = movementType;
    }

    public MovementType getMovementType() {
        return movementType;
    }
}
