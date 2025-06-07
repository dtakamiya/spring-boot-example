package com.example.demo.domain

import spock.lang.Specification

/**
 * CustomerエンティティのSpock単体テスト。
 */
class CustomerSpec extends Specification {
    def "顧客情報が正しく保持される"() {
        when:
        def customer = new Customer(1L, "山田太郎")
        then:
        customer.id == 1L
        customer.name == "山田太郎"
    }
} 