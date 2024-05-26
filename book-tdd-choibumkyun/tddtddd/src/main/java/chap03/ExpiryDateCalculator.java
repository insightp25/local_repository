package chap03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpiryDateCalculator {
    public LocalDate calculateExpiryDate(PayData payData) {
        final int paidAmount = payData.getPaidAmount();

        int addedMonths = paidAmount >= 100_000
            ? 12 * (paidAmount / 100_000) + (paidAmount % 100_000) / 10_000
            : paidAmount / 10_000;

        if (payData.getFirstBillingDate() != null) {
            return expiryDateUsingFirstBillingDate(payData, addedMonths);

        } else {
            return expiryDateUsingBillingDate(payData, addedMonths);
        }
    }

    private static LocalDate expiryDateUsingBillingDate(PayData payData, int addedMonths) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);

        final int dayOfBilling = payData.getBillingDate().getDayOfMonth();
        final int dayLenOfBillingMon = getLastDayOfMonth(payData.getBillingDate());
        final int dayLenOfCandMon = getLastDayOfMonth(candidateExp);

        if (dayOfBilling == dayLenOfBillingMon) {
            return candidateExp.withDayOfMonth(dayLenOfCandMon);
        }

        return candidateExp;
    }

    private static LocalDate expiryDateUsingFirstBillingDate(PayData payData, int addedMonths) {
        LocalDate candidateExp = payData.getBillingDate().plusMonths(addedMonths);

        if (!isSameDayOfMonth(payData.getFirstBillingDate(), candidateExp)) {
            final int dayOfFirstBilling = payData.getFirstBillingDate().getDayOfMonth();
            final int dayLenOfCandMon = getLastDayOfMonth(candidateExp);

            if (dayLenOfCandMon < dayOfFirstBilling) {
                return candidateExp.withDayOfMonth(dayLenOfCandMon);
            }

            return candidateExp.withDayOfMonth(dayOfFirstBilling);

        } else {
            return candidateExp;
        }
    }

    private static boolean isSameDayOfMonth(LocalDate date1, LocalDate date2) {
        return date1.getDayOfMonth() == date2.getDayOfMonth();
    }

    private static int getLastDayOfMonth(LocalDate candidateExp) {
        return YearMonth.from(candidateExp).lengthOfMonth();
    }
}
