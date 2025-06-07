package com.example.demo.domain;

import java.util.List;

/**
 * 取引履歴エンティティのリポジトリ。
 * 取引履歴の検索や保存の抽象インターフェース。
 */
public interface TransactionRepository {
    /**
     * 口座IDで取引履歴を検索
     */
    List<Transaction> findByAccountId(Long accountId);
    /**
     * 取引履歴を保存
     */
    void save(Transaction transaction);
} 