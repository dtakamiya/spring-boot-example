package com.example.demo.application;

import com.example.demo.domain.Account;
import com.example.demo.domain.AccountRepository;
import org.springframework.stereotype.Service;

/**
 * 口座開設ユースケースのアプリケーションサービス。
 * 新規口座を作成し、リポジトリに保存する。
 */
@Service
public class OpenAccountService {
    private final AccountRepository accountRepository;

    public OpenAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * 口座開設
     * @param id 口座ID
     * @param name 口座名義
     * @param initialBalance 初期残高
     * @return 作成された口座
     */
    public Account open(Long id, String name, int initialBalance) {
        Account account = new Account(id, name, initialBalance);
        accountRepository.update(account);
        return account;
    }
} 