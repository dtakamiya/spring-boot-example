package com.example.demo.application;

import com.example.demo.domain.Account;
import com.example.demo.domain.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 口座間振込ユースケースのアプリケーションサービス。
 * DDDのアプリケーション層として、ドメインサービスやリポジトリを協調させてユースケースを実現する。
 */
@Service
public class TransferService {
    private final AccountRepository accountRepository;
    private final NotificationService notificationService;
    private final ExchangeRateService exchangeRateService;

    /**
     * コンストラクタ
     * @param accountRepository 口座リポジトリ
     * @param notificationService 外部通知サービス
     * @param exchangeRateService 為替レート取得サービス
     */
    public TransferService(AccountRepository accountRepository, NotificationService notificationService, ExchangeRateService exchangeRateService) {
        this.accountRepository = accountRepository;
        this.notificationService = notificationService;
        this.exchangeRateService = exchangeRateService;
    }

    /**
     * 口座間の振込処理（トランザクション管理あり）
     * @param fromId 送金元口座ID
     * @param toId 送金先口座ID
     * @param amount 振込金額
     * @throws IllegalArgumentException 残高不足や口座未存在時
     */
    @Transactional
    public void transfer(Long fromId, Long toId, int amount) {
        Account from = accountRepository.findById(fromId)
                .orElseThrow(() -> new IllegalArgumentException("送金元口座が存在しません"));
        Account to = accountRepository.findById(toId)
                .orElseThrow(() -> new IllegalArgumentException("送金先口座が存在しません"));
        if (from.getBalance() < amount) throw new IllegalArgumentException("残高不足です");
        from.withdraw(amount);
        to.deposit(amount);
        accountRepository.update(from);
        accountRepository.update(to);
        // 振込完了後に外部通知サービスを呼び出す
        notificationService.notifyTransfer(fromId, toId, amount);
    }

    /**
     * 為替レートを考慮した口座間振込処理（DB更新前に外部サービスから情報取得）
     * @param fromId 送金元口座ID
     * @param toId 送金先口座ID
     * @param amount 振込金額（送金元通貨単位）
     * @param fromCurrency 送金元通貨（例：USD）
     * @param toCurrency 送金先通貨（例：JPY）
     */
    @Transactional
    public void transferWithExchange(Long fromId, Long toId, int amount, String fromCurrency, String toCurrency) {
        // 1. 外部サービスから為替レートを取得
        double rate = exchangeRateService.getRate(fromCurrency, toCurrency).getRate();
        // 2. 換算後の金額を計算
        int convertedAmount = (int)Math.round(amount * rate);
        // 3. 通常の振込処理（換算後金額で）
        Account from = accountRepository.findById(fromId)
                .orElseThrow(() -> new IllegalArgumentException("送金元口座が存在しません"));
        Account to = accountRepository.findById(toId)
                .orElseThrow(() -> new IllegalArgumentException("送金先口座が存在しません"));
        if (from.getBalance() < amount) throw new IllegalArgumentException("残高不足です");
        from.withdraw(amount);
        to.deposit(convertedAmount);
        accountRepository.update(from);
        accountRepository.update(to);
        // 振込完了後に外部通知サービスを呼び出す
        notificationService.notifyTransfer(fromId, toId, amount);
    }
} 