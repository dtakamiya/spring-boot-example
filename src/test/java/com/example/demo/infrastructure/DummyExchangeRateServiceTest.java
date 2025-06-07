package com.example.demo.infrastructure;

import com.example.demo.domain.ExchangeRate;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DummyExchangeRateServiceの単体テスト。
 * USD→JPYは150.0、それ以外は1.0となることを検証する。
 */
class DummyExchangeRateServiceTest {
    @Test
    void USDからJPYは150が返る() {
        DummyExchangeRateService service = new DummyExchangeRateService();
        ExchangeRate rate = service.getRate("USD", "JPY");
        assertThat(rate.getRate()).isEqualTo(150.0);
    }

    @Test
    void その他の通貨ペアは1が返る() {
        DummyExchangeRateService service = new DummyExchangeRateService();
        ExchangeRate rate = service.getRate("EUR", "USD");
        assertThat(rate.getRate()).isEqualTo(1.0);
    }
} 