package com.example.demo.domain;

import java.util.Optional;

/**
 * 顧客エンティティのリポジトリ。
 * 顧客の検索や永続化の抽象インターフェース。
 */
public interface CustomerRepository {
    /**
     * 顧客IDで検索
     */
    Optional<Customer> findById(Long id);
    /**
     * 顧客情報を保存
     */
    void save(Customer customer);
} 