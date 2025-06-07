package com.example.demo.application;

import com.example.demo.domain.ExchangeRate;

/**
 * 外部サービスから為替レートを取得するサービスのインターフェース。
 * DDDのアプリケーション層で外部情報取得の抽象化を担う。
 */
public interface ExchangeRateService {
    /**
     * 指定した通貨ペアの為替レートを取得する
     * @param from 基準通貨（例：USD）
     * @param to 変換先通貨（例：JPY）
     * @return 為替レート（例：1USD=150JPY）
     */
    ExchangeRate getRate(String from, String to);
} 