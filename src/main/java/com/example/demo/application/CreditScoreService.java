package com.example.demo.application;

/**
 * 外部サービスから信用スコアを取得するサービスのインターフェース。
 * 金融ドメインでの与信判断等に利用。
 */
public interface CreditScoreService {
    /**
     * 顧客IDから信用スコアを取得
     * @param customerId 顧客ID
     * @return 信用スコア（例：300～850）
     */
    int getScore(Long customerId);
} 