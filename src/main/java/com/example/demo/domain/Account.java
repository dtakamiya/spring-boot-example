package com.example.demo.domain;

/**
 * 口座エンティティ。
 * DDDのエンティティとして、口座ID・名義・残高などの属性と、
 * ドメインロジック（入金・出金）を持つ。
 */
public class Account {
    /** 口座ID（エンティティの識別子） */
    private Long id;
    /** 口座名義 */
    private String name;
    /** 残高（単位: 円） */
    private int balance;

    public Account() {}

    /**
     * コンストラクタ
     * @param id 口座ID
     * @param name 口座名義
     * @param balance 初期残高
     */
    public Account(Long id, String name, int balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getBalance() { return balance; }
    public void setBalance(int balance) { this.balance = balance; }

    /**
     * 出金処理（ドメインロジック）
     * @param amount 出金額
     */
    public void withdraw(int amount) { this.balance -= amount; }

    /**
     * 入金処理（ドメインロジック）
     * @param amount 入金額
     */
    public void deposit(int amount) { this.balance += amount; }
} 