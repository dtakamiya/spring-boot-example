package com.example.demo.application

import spock.lang.Specification

/**
 * ダミー信用スコアサービスのSpock単体テスト。
 */
class DummyCreditScoreServiceSpec extends Specification {
    def "信用スコアが常に700で返る"() {
        given:
        def service = new DummyCreditScoreService()
        when:
        def score = service.getCreditScore(1L)
        then:
        score == 700
    }
} 