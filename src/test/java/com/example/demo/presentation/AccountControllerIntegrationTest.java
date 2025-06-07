package com.example.demo.presentation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * AccountControllerの結合テスト（SpringBootTest）。
 * 実際のDI・DB・myBatisを使い、APIの正常系・異常系を検証する。
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountControllerIntegrationTest {
    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void 振込API_正常系() {
        String url = "http://localhost:" + port + "/api/accounts/transfer?fromId=1&toId=2&amount=1000";
        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).contains("振込成功");
    }

    @Test
    void 振込API_残高不足() {
        String url = "http://localhost:" + port + "/api/accounts/transfer?fromId=1&toId=2&amount=100000000";
        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
        assertThat(response.getStatusCode().is4xxClientError()).isTrue();
        assertThat(response.getBody()).contains("残高不足");
    }

    @Test
    void 振込API_送金元口座未存在() {
        String url = "http://localhost:" + port + "/api/accounts/transfer?fromId=999&toId=2&amount=1000";
        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
        assertThat(response.getStatusCode().is4xxClientError()).isTrue();
        assertThat(response.getBody()).contains("送金元口座が存在しません");
    }

    @Test
    void 為替レート考慮振込API_正常系() {
        // 1USD=150JPY, 送金元1の残高は100000なので十分
        String url = "http://localhost:" + port + "/api/accounts/transfer-with-exchange?fromId=1&toId=2&amount=10&fromCurrency=USD&toCurrency=JPY";
        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).contains("為替レート考慮振込成功");
    }

    @Test
    void 為替レート考慮振込API_残高不足() {
        // 送金元1の残高は100000、1000000USD送金は残高不足
        String url = "http://localhost:" + port + "/api/accounts/transfer-with-exchange?fromId=1&toId=2&amount=1000000&fromCurrency=USD&toCurrency=JPY";
        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
        assertThat(response.getStatusCode().is4xxClientError()).isTrue();
        assertThat(response.getBody()).contains("残高不足");
    }

    @Test
    void 為替レート考慮振込API_送金元口座未存在() {
        String url = "http://localhost:" + port + "/api/accounts/transfer-with-exchange?fromId=999&toId=2&amount=10&fromCurrency=USD&toCurrency=JPY";
        ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
        assertThat(response.getStatusCode().is4xxClientError()).isTrue();
        assertThat(response.getBody()).contains("送金元口座が存在しません");
    }
} 