package com.example.demo.domain;

/**
 * 顧客エンティティ。
 * 金融ドメインにおける顧客情報（ID・氏名など）を表す。
 */
public class Customer {
    private final Long id;
    private final String name;

    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    public Long getId() { return id; }
    public String getName() { return name; }
} 