package com.example.demo.application;

import com.example.demo.domain.Account;
import com.example.demo.domain.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

/**
 * BalanceInquiryServiceの単体テスト。
 */
class BalanceInquiryServiceTest {
    AccountRepository accountRepository;
    BalanceInquiryService service;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        service = new BalanceInquiryService(accountRepository);
    }

    @Test
    void 残高が正しく取得できる() {
        when(accountRepository.findById(1L)).thenReturn(Optional.of(new Account(1L, "テスト", 5000)));
        int balance = service.getBalance(1L);
        assertThat(balance).isEqualTo(5000);
    }

    @Test
    void 口座が存在しない場合は例外() {
        when(accountRepository.findById(1L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.getBalance(1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("口座が存在しません");
    }
} 