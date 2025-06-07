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

    /**
     * コンストラクタ
     * @param accountRepository 口座リポジトリ
     * @param notificationService 外部通知サービス
     */
    public TransferService(AccountRepository accountRepository, NotificationService notificationService) {
        this.accountRepository = accountRepository;
        this.notificationService = notificationService;
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
} 