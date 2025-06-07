package com.example.demo.application;

import com.example.demo.domain.Account;
import com.example.demo.domain.AccountRepository;
import org.springframework.stereotype.Service;

/**
 * 残高照会ユースケースのアプリケーションサービス。
 * 指定口座の残高を返す。
 */
@Service
public class BalanceInquiryService {
    private final AccountRepository accountRepository;

    public BalanceInquiryService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * 残高照会
     * @param accountId 口座ID
     * @return 残高
     */
    public int getBalance(Long accountId) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new IllegalArgumentException("口座が存在しません"));
        return account.getBalance();
    }
} 