package chap07.debit_registerer.domain;

import chap07.debit_registerer.util.CardValidity;

public class RegisterResult {
    private final CardValidity validity;

    public RegisterResult(CardValidity validity) {
        this.validity = validity;
    }

    public static RegisterResult error(CardValidity validity) {
        return new RegisterResult(CardValidity.ERROR);
    }

    public static RegisterResult success() {
        return new RegisterResult(CardValidity.VALID);
    }

    public static RegisterResult theft(CardValidity validity) {
        return new RegisterResult(CardValidity.THEFT);
    }

    public static RegisterResult invalid(CardValidity validity) {
        return new RegisterResult(CardValidity.INVALID);
    }

    public CardValidity getValidity() {
        return this.validity;
    }
}
