package com.example.demo.domain;

/**
 * 為替レートを表す値オブジェクト。
 * 例：1ドル＝150円 の場合、from="USD", to="JPY", rate=150.0 となる。
 */
public class ExchangeRate {
    /** 基準通貨（例：USD） */
    private final String from;
    /** 変換先通貨（例：JPY） */
    private final String to;
    /** レート（例：150.0） */
    private final double rate;

    public ExchangeRate(String from, String to, double rate) {
        this.from = from;
        this.to = to;
        this.rate = rate;
    }
    public String getFrom() { return from; }
    public String getTo() { return to; }
    public double getRate() { return rate; }
} 