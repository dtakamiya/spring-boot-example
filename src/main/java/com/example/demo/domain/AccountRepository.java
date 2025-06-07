package com.example.demo.domain;

import java.util.Optional;

/**
 * 口座エンティティのリポジトリ。
 * DDDのリポジトリとして、永続化や検索の抽象インターフェースを提供する。
 */
public interface AccountRepository {
    /**
     * 口座IDで口座を検索する
     * @param id 口座ID
     * @return 該当口座（存在しない場合は空）
     */
    Optional<Account> findById(Long id);

    /**
     * 口座情報を更新する
     * @param account 更新対象の口座
     */
    void update(Account account);
} 