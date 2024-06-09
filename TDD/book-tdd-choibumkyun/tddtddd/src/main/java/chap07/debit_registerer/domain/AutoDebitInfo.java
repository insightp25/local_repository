package chap07.debit_registerer.domain;

import java.time.LocalDateTime;

public class AutoDebitInfo {
    private final String userId;
    private String cardNumber;
    private final LocalDateTime createdAt;

    public AutoDebitInfo(String userId, String cardNumber, LocalDateTime createdAt) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.createdAt = createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void changeCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
