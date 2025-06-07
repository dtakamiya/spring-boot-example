package com.example.demo.application;

import com.example.demo.domain.Account;
import com.example.demo.domain.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * TransferServiceの単体テスト。
 * 依存するリポジトリ・通知サービスはモック化し、振込ロジックの正常系・異常系を検証する。
 */
class TransferServiceTest {
    AccountRepository accountRepository;
    NotificationService notificationService;
    TransferService transferService;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        notificationService = mock(NotificationService.class);
        transferService = new TransferService(accountRepository, notificationService);
    }

    @Test
    void 振込が正常に完了し通知が呼ばれる() {
        Account from = new Account(1L, "送金元", 10000);
        Account to = new Account(2L, "送金先", 5000);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(from));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(to));

        transferService.transfer(1L, 2L, 3000);

        // 残高が正しく更新される
        assertThat(from.getBalance()).isEqualTo(7000);
        assertThat(to.getBalance()).isEqualTo(8000);
        // 更新が2回呼ばれる
        verify(accountRepository, times(2)).update(any(Account.class));
        // 通知サービスが呼ばれる
        verify(notificationService).notifyTransfer(1L, 2L, 3000);
    }

    @Test
    void 残高不足の場合は例外が発生し通知されない() {
        Account from = new Account(1L, "送金元", 1000);
        Account to = new Account(2L, "送金先", 5000);
        when(accountRepository.findById(1L)).thenReturn(Optional.of(from));
        when(accountRepository.findById(2L)).thenReturn(Optional.of(to));

        assertThatThrownBy(() -> transferService.transfer(1L, 2L, 2000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("残高不足");
        // 通知サービスは呼ばれない
        verify(notificationService, never()).notifyTransfer(any(), any(), anyInt());
    }

    @Test
    void 送金元口座が存在しない場合は例外() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());
        when(accountRepository.findById(2L)).thenReturn(Optional.of(new Account(2L, "送金先", 5000)));

        assertThatThrownBy(() -> transferService.transfer(1L, 2L, 1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("送金元口座が存在しません");
        verify(notificationService, never()).notifyTransfer(any(), any(), anyInt());
    }

    @Test
    void 送金先口座が存在しない場合は例外() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(new Account(1L, "送金元", 1000)));
        when(accountRepository.findById(2L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> transferService.transfer(1L, 2L, 1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("送金先口座が存在しません");
        verify(notificationService, never()).notifyTransfer(any(), any(), anyInt());
    }
} 