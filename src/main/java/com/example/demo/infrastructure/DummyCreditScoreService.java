package com.example.demo.infrastructure;

import com.example.demo.application.CreditScoreService;
import org.springframework.stereotype.Service;

/**
 * 信用スコア取得サービスのダミー実装。
 * 実際の外部API連携の代わりに、顧客IDに応じた固定値を返す。
 */
@Service
public class DummyCreditScoreService implements CreditScoreService {
    @Override
    public int getScore(Long customerId) {
        // サンプル：IDが偶数なら700、奇数なら600を返す
        return (customerId % 2 == 0) ? 700 : 600;
    }
} 