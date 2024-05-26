package chap03;

import java.time.LocalDate;
import java.util.Locale.Builder;

public class PayData {
    private LocalDate firstBillingDate;
    private LocalDate billingDate;
    private int paidAmount;

    private PayData() {};

    public PayData(LocalDate firstBillingDate, LocalDate billingDate, int paidAmount) {
        this.firstBillingDate = firstBillingDate;
        this.billingDate = billingDate;
        this.paidAmount = paidAmount;
    }

    public LocalDate getFirstBillingDate() {
        return firstBillingDate;
    }

    public LocalDate getBillingDate() {
        return billingDate;
    }

    public int getPaidAmount() {
        return paidAmount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PayData data = new PayData();

        public Builder firstBillingDate(LocalDate firstBillingDate) {
            data.firstBillingDate = firstBillingDate;
            return this;
        }

        public Builder billingDate(LocalDate billingDate) {
            data.billingDate = billingDate;
            return this;
        }

        public Builder paidAmount(int paidAmount) {
            data.paidAmount = paidAmount;
            return this;
        }

        public PayData build() {
            return data;
        }
    }
}
