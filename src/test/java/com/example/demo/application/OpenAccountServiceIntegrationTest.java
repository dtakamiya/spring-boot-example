package com.example.demo.application;

import com.example.demo.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * OpenAccountServiceの結合テスト（SpringBootTest）。
 */
@SpringBootTest
class OpenAccountServiceIntegrationTest {
    @Autowired
    OpenAccountService service;

    @Test
    void 新規口座が開設できる() {
        Account account = service.open(100L, "テスト新規", 5000);
        assertThat(account.getId()).isEqualTo(100L);
        assertThat(account.getName()).isEqualTo("テスト新規");
        assertThat(account.getBalance()).isEqualTo(5000);
    }
} 