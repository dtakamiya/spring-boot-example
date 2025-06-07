package com.example.demo.domain;

import java.time.LocalDateTime;

/**
 * 取引履歴エンティティ。
 * 金融ドメインにおける口座の入出金や振込などの履歴を表す。
 */
public class Transaction {
    private final Long id;
    private final Long accountId;
    private final TransactionType type;
    private final int amount;
    private final LocalDateTime date;

    public Transaction(Long id, Long accountId, TransactionType type, int amount, LocalDateTime date) {
        this.id = id;
        this.accountId = accountId;
        this.type = type;
        this.amount = amount;
        this.date = date;
    }
    public Long getId() { return id; }
    public Long getAccountId() { return accountId; }
    public TransactionType getType() { return type; }
    public int getAmount() { return amount; }
    public LocalDateTime getDate() { return date; }
} 