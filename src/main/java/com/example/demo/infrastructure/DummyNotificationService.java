package com.example.demo.infrastructure;

import com.example.demo.application.NotificationService;
import org.springframework.stereotype.Service;

/**
 * 外部通知サービスのダミー実装例。
 * 実際の外部API連携の代わりにログ出力のみ行う。
 */
@Service
public class DummyNotificationService implements NotificationService {
    @Override
    public void notifyTransfer(Long fromId, Long toId, int amount) {
        System.out.println("[外部通知] 振込完了: fromId=" + fromId + ", toId=" + toId + ", amount=" + amount);
    }
} 