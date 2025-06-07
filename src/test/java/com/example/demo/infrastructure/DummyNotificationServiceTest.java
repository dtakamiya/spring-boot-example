package com.example.demo.infrastructure;

import com.example.demo.application.NotificationService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DummyNotificationServiceの単体テスト。
 * notifyTransferが呼ばれると標準出力に通知内容が出力されることを検証する。
 */
class DummyNotificationServiceTest {
    @Test
    void notifyTransferで標準出力に通知内容が出力される() {
        NotificationService service = new DummyNotificationService();
        // 標準出力の内容を一時的にキャプチャ
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        service.notifyTransfer(1L, 2L, 5000);
        String output = out.toString();
        // 内容に期待する文字列が含まれることを確認
        assertThat(output).contains("[外部通知] 振込完了: fromId=1, toId=2, amount=5000");
        // 標準出力を元に戻す
        System.setOut(new java.io.PrintStream(new java.io.FileOutputStream(java.io.FileDescriptor.out)));
    }
} 