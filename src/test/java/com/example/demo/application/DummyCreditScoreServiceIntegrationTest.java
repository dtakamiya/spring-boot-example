package com.example.demo.application;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DummyCreditScoreServiceの結合テスト（SpringBootTest）。
 */
@SpringBootTest
class DummyCreditScoreServiceIntegrationTest {
    @Autowired
    CreditScoreService service;

    @Test
    void DI経由でスコアが取得できる() {
        assertThat(service.getScore(2L)).isEqualTo(700);
        assertThat(service.getScore(3L)).isEqualTo(600);
    }
} 