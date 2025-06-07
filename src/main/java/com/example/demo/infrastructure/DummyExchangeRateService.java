package com.example.demo.infrastructure;

import com.example.demo.application.ExchangeRateService;
import com.example.demo.domain.ExchangeRate;
import org.springframework.stereotype.Service;

/**
 * 外部為替レートサービスのダミー実装例。
 * 実際の外部API連携の代わりに、固定値（例：1USD=150JPY）を返す。
 */
@Service
public class DummyExchangeRateService implements ExchangeRateService {
    @Override
    public ExchangeRate getRate(String from, String to) {
        // サンプルとしてUSD→JPYのみ固定値を返す。他は1.0とする。
        if ("USD".equalsIgnoreCase(from) && "JPY".equalsIgnoreCase(to)) {
            return new ExchangeRate(from, to, 150.0);
        }
        return new ExchangeRate(from, to, 1.0);
    }
} 