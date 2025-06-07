package com.example.demo.infrastructure;

import com.example.demo.domain.Account;
import com.example.demo.domain.AccountRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 口座リポジトリのmyBatis実装。
 * DDDのインフラ層として、永続化の具体的な処理を担う。
 */
@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountMapper accountMapper;

    /**
     * コンストラクタ
     * @param accountMapper myBatisのMapper
     */
    public AccountRepositoryImpl(AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }

    /**
     * 口座IDで口座を検索
     * @param id 口座ID
     * @return 該当口座（存在しない場合は空）
     */
    @Override
    public Optional<Account> findById(Long id) {
        return Optional.ofNullable(accountMapper.findById(id));
    }

    /**
     * 口座情報を更新
     * @param account 更新対象の口座
     */
    @Override
    public void update(Account account) {
        accountMapper.update(account);
    }
} 