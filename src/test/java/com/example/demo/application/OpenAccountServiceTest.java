package com.example.demo.application;

import com.example.demo.domain.Account;
import com.example.demo.domain.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

/**
 * OpenAccountServiceの単体テスト。
 */
class OpenAccountServiceTest {
    AccountRepository accountRepository;
    OpenAccountService service;

    @BeforeEach
    void setUp() {
        accountRepository = mock(AccountRepository.class);
        service = new OpenAccountService(accountRepository);
    }

    @Test
    void 口座が正しく作成されリポジトリに保存される() {
        Account account = service.open(10L, "新規顧客", 10000);
        assertThat(account.getId()).isEqualTo(10L);
        assertThat(account.getName()).isEqualTo("新規顧客");
        assertThat(account.getBalance()).isEqualTo(10000);
        verify(accountRepository).update(account);
    }
} 