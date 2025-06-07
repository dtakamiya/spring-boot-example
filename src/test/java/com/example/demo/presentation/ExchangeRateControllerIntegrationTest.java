package com.example.demo.presentation;

import com.example.demo.domain.ExchangeRate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * ExchangeRateControllerの結合テスト（SpringBootTest）。
 * 実際のDI・DB・myBatisを使い、APIの正常系・異常系を検証する。
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExchangeRateControllerIntegrationTest {
    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void 為替レート取得API_USDからJPY() {
        String url = "http://localhost:" + port + "/api/exchange-rate?from=USD&to=JPY";
        ResponseEntity<ExchangeRate> response = restTemplate.getForEntity(url, ExchangeRate.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getRate()).isEqualTo(150.0);
    }

    @Test
    void 為替レート取得API_未対応通貨ペア() {
        String url = "http://localhost:" + port + "/api/exchange-rate?from=EUR&to=USD";
        ResponseEntity<ExchangeRate> response = restTemplate.getForEntity(url, ExchangeRate.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getRate()).isEqualTo(1.0);
    }
} 