classDiagram
    class Account {
        +id: Long
        +name: String
        +balance: int
        +withdraw(amount)
        +deposit(amount)
    }
    class ExchangeRate {
        +from: String
        +to: String
        +rate: double
    }
    interface AccountRepository {
        +findById(id)
        +update(account)
    }
    interface NotificationService {
        +notifyTransfer(fromId, toId, amount)
    }
    interface ExchangeRateService {
        +getRate(from, to)
    }
    class AccountRepositoryImpl
    class DummyNotificationService
    class DummyExchangeRateService
    class TransferService {
        -accountRepository
        -notificationService
        +transfer(fromId, toId, amount)
    }
    class ExchangeRateController
    class AccountController

    AccountRepository <|.. AccountRepositoryImpl
    NotificationService <|.. DummyNotificationService
    ExchangeRateService <|.. DummyExchangeRateService
    TransferService --> AccountRepository
    TransferService --> NotificationService
    AccountController --> TransferService
    ExchangeRateController --> ExchangeRateService 