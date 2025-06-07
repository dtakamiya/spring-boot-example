package com.example.demo.application

import com.example.demo.domain.Account
import com.example.demo.domain.AccountRepository
import spock.lang.Specification

/**
 * 口座開設サービスのSpock単体テスト。
 */
class OpenAccountServiceSpec extends Specification {
    def "新規口座が作成されリポジトリに保存される"() {
        given:
        def repo = Mock(AccountRepository)
        def service = new OpenAccountService(repo)
        when:
        def account = service.openAccount(1L, 5000L)
        then:
        1 * repo.save(_ as Account)
        account.id == 1L
        account.balance == 5000L
    }
} 