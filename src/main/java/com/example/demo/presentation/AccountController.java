package com.example.demo.presentation;

import com.example.demo.application.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 口座関連APIのコントローラー。
 * DDDのプレゼンテーション層として、ユースケースサービスを呼び出しAPIエンドポイントを提供する。
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    private final TransferService transferService;

    /**
     * コンストラクタ
     * @param transferService 振込ユースケースサービス
     */
    public AccountController(TransferService transferService) {
        this.transferService = transferService;
    }

    /**
     * 口座間振込API
     * @param fromId 送金元口座ID
     * @param toId 送金先口座ID
     * @param amount 振込金額
     * @return 結果メッセージ
     */
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestParam Long fromId, @RequestParam Long toId, @RequestParam int amount) {
        try {
            transferService.transfer(fromId, toId, amount);
            return ResponseEntity.ok("振込成功");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * 為替レート取得を伴う口座間振込API
     * @param fromId 送金元口座ID
     * @param toId 送金先口座ID
     * @param amount 振込金額（送金元通貨単位）
     * @param fromCurrency 送金元通貨（例：USD）
     * @param toCurrency 送金先通貨（例：JPY）
     * @return 結果メッセージ
     */
    @PostMapping("/transfer-with-exchange")
    public ResponseEntity<String> transferWithExchange(
            @RequestParam Long fromId,
            @RequestParam Long toId,
            @RequestParam int amount,
            @RequestParam String fromCurrency,
            @RequestParam String toCurrency) {
        try {
            transferService.transferWithExchange(fromId, toId, amount, fromCurrency, toCurrency);
            return ResponseEntity.ok("為替レート考慮振込成功");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
} 