package com.example.demo.domain;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Transactionエンティティの単体テスト。
 */
class TransactionTest {
    @Test
    void 取引情報が正しく保持される() {
        LocalDateTime now = LocalDateTime.now();
        Transaction tx = new Transaction(10L, 1L, TransactionType.DEPOSIT, 5000, now);
        assertThat(tx.getId()).isEqualTo(10L);
        assertThat(tx.getAccountId()).isEqualTo(1L);
        assertThat(tx.getType()).isEqualTo(TransactionType.DEPOSIT);
        assertThat(tx.getAmount()).isEqualTo(5000);
        assertThat(tx.getDate()).isEqualTo(now);
    }
} 