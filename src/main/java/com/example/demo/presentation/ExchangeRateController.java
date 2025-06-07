package com.example.demo.presentation;

import com.example.demo.application.ExchangeRateService;
import com.example.demo.domain.ExchangeRate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 為替レート取得APIのコントローラー。
 * 外部サービス連携のサンプルとして、指定通貨ペアのレートを取得するエンドポイントを提供する。
 * 例: /api/exchange-rate?from=USD&to=JPY
 */
@RestController
@RequestMapping("/api/exchange-rate")
public class ExchangeRateController {
    private final ExchangeRateService exchangeRateService;

    /**
     * コンストラクタ
     * @param exchangeRateService 為替レート取得サービス
     */
    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    /**
     * 為替レート取得API
     * @param from 基準通貨（例：USD）
     * @param to 変換先通貨（例：JPY）
     * @return 為替レート情報（JSON）
     */
    @GetMapping
    public ExchangeRate getRate(@RequestParam String from, @RequestParam String to) {
        return exchangeRateService.getRate(from, to);
    }
} 