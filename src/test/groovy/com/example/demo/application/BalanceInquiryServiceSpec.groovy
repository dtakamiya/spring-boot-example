package com.example.demo.application

import com.example.demo.domain.Account
import com.example.demo.domain.AccountRepository
import spock.lang.Specification

/**
 * 残高照会サービスのSpock単体テスト。
 */
class BalanceInquiryServiceSpec extends Specification {
    def "口座IDで残高を取得できる"() {
        given:
        def repo = Mock(AccountRepository)
        def service = new BalanceInquiryService(repo)
        repo.findById(1L) >> Optional.of(new Account(1L, 1000L))
        when:
        def balance = service.getBalance(1L)
        then:
        balance == 1000L
    }

    def "存在しない口座IDの場合は例外"() {
        given:
        def repo = Mock(AccountRepository)
        def service = new BalanceInquiryService(repo)
        repo.findById(999L) >> Optional.empty()
        when:
        service.getBalance(999L)
        then:
        thrown(IllegalArgumentException)
    }
} 