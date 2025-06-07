package com.example.demo.application;

/**
 * 振込完了時に外部通知を行うサービスのインターフェース。
 * DDDのアプリケーション層で外部サービス連携の抽象化を担う。
 */
public interface NotificationService {
    /**
     * 振込完了通知を送信する
     * @param fromId 送金元口座ID
     * @param toId 送金先口座ID
     * @param amount 振込金額
     */
    void notifyTransfer(Long fromId, Long toId, int amount);
} 