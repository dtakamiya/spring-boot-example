package com.example.demo.application;

import com.example.demo.infrastructure.DummyCreditScoreService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DummyCreditScoreServiceの単体テスト。
 */
class DummyCreditScoreServiceTest {
    @Test
    void 偶数IDは700_奇数IDは600が返る() {
        DummyCreditScoreService service = new DummyCreditScoreService();
        assertThat(service.getScore(2L)).isEqualTo(700);
        assertThat(service.getScore(3L)).isEqualTo(600);
    }
} 