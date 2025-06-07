package com.example.demo.domain

import spock.lang.Specification
import java.time.LocalDateTime

/**
 * TransactionエンティティのSpock単体テスト。
 */
class TransactionSpec extends Specification {
    def "取引情報が正しく保持される"() {
        given:
        def now = LocalDateTime.now()
        when:
        def tx = new Transaction(1L, 2L, 1000L, TransactionType.TRANSFER, now)
        then:
        tx.fromAccountId == 1L
        tx.toAccountId == 2L
        tx.amount == 1000L
        tx.type == TransactionType.TRANSFER
        tx.timestamp == now
    }
} 