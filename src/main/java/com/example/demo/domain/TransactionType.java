package com.example.demo.domain;

/**
 * 取引種別（入金・出金・振込など）を表す列挙型。
 */
public enum TransactionType {
    DEPOSIT,    // 入金
    WITHDRAWAL, // 出金
    TRANSFER    // 振込
} 