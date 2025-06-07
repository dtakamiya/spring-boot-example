package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Accountエンティティの単体テスト。
 * 入金・出金ロジックの正常系・異常系を検証する。
 */
class AccountTest {
    @Test
    void 入金処理で残高が増加する() {
        Account account = new Account(1L, "テスト太郎", 1000);
        account.deposit(500);
        assertThat(account.getBalance()).isEqualTo(1500);
    }

    @Test
    void 出金処理で残高が減少する() {
        Account account = new Account(1L, "テスト太郎", 1000);
        account.withdraw(400);
        assertThat(account.getBalance()).isEqualTo(600);
    }
} 