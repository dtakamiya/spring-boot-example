package com.example.demo.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * BalanceInquiryServiceの結合テスト（SpringBootTest）。
 */
@SpringBootTest
class BalanceInquiryServiceIntegrationTest {
    @Autowired
    BalanceInquiryService service;

    @Test
    void 残高が正しく取得できる() {
        int balance = service.getBalance(1L); // 事前データで存在するID
        assertThat(balance).isGreaterThanOrEqualTo(0);
    }

    @Test
    void 存在しない口座IDは例外() {
        assertThatThrownBy(() -> service.getBalance(9999L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("口座が存在しません");
    }
} 