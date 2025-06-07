package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Customerエンティティの単体テスト。
 */
class CustomerTest {
    @Test
    void 顧客情報が正しく保持される() {
        Customer customer = new Customer(1L, "山田太郎");
        assertThat(customer.getId()).isEqualTo(1L);
        assertThat(customer.getName()).isEqualTo("山田太郎");
    }
} 