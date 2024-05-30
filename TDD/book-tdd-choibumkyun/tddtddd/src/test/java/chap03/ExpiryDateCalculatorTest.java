package chap03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class ExpiryDateCalculatorTest {

    @Test
    void pay_10000_won_then_expires_1_month_later() {
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2019, 3, 1))
                .paidAmount(10_000)
                .build(),
            LocalDate.of(2019, 4, 1));

        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2019, 5, 1))
                .paidAmount(10_000)
                .build(),
            LocalDate.of(2019, 6, 1));
    }

    @Test
    void paid_date_not_same_as_expiry_date() {
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2019, 1, 31))
                .paidAmount(10_000)
                .build(),
            LocalDate.of(2019, 2, 28)
        );
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2019, 5, 31))
                .paidAmount(10_000)
                .build(),
            LocalDate.of(2019, 6, 30)
        );
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2024, 1, 31))
                .paidAmount(10_000)
                .build(),
            LocalDate.of(2024, 2, 29)
        );
    }

    @Test
    void pay_10000_won_when_first_paid_date_diff_from_exp_date() {
        PayData payData = PayData.builder()
            .firstBillingDate(LocalDate.of(2024, 1, 31))
            .billingDate(LocalDate.of(2024, 2, 29))
            .paidAmount(10_000)
            .build();
        assertExpiryDate(payData, LocalDate.of(2024, 3, 31));

        PayData payData2 = PayData.builder()
            .firstBillingDate(LocalDate.of(2024, 1, 30))
            .billingDate(LocalDate.of(2024, 2, 29))
            .paidAmount(10_000)
            .build();
        assertExpiryDate(payData2, LocalDate.of(2024, 3, 30));

        PayData payData3 = PayData.builder()
            .firstBillingDate(LocalDate.of(2024, 5, 31))
            .billingDate(LocalDate.of(2024, 6, 30))
            .paidAmount(10_000)
            .build();
        assertExpiryDate(payData3, LocalDate.of(2024, 7, 31));
    }

    @Test
    void calculate_proportionately_when_paid_over_20000_won() {
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2024, 3, 1))
                .paidAmount(20_000)
                .build(),
            LocalDate.of(2024, 5, 1)
        );

        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2024, 3, 1))
                .paidAmount(30_000)
                .build(),
            LocalDate.of(2024, 6, 1)
        );

        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2024, 3, 1))
                .paidAmount(70_000)
                .build(),
            LocalDate.of(2024, 10, 1)
        );
    }

    @Test
    void calculate_when_paid_over_20000_won_while_first_pay_date_differs_from_expiry_date() {
        assertExpiryDate(
            PayData.builder()
                .firstBillingDate(LocalDate.of(2024, 1, 31))
                .billingDate(LocalDate.of(2024, 2, 28))
                .paidAmount(20_000)
                .build(),
            LocalDate.of(2024, 4, 30)
        );

        assertExpiryDate(
            PayData.builder()
                .firstBillingDate(LocalDate.of(2024, 3, 31))
                .billingDate(LocalDate.of(2024, 6, 30))
                .paidAmount(20_000)
                .build(),
            LocalDate.of(2024, 8, 31)
        );
    }

    @Test
    void provide_1_year_when_paid_100000() {
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2024, 1, 30))
                .paidAmount(100_000)
                .build(),
            LocalDate.of(2025, 1, 30)
        );
    }

    @Test
    void provide_1_year_when_paid_100000_at_the_end_of_month() {
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2024, 2, 29))
                .paidAmount(100_000)
                .build(),
            LocalDate.of(2025, 2, 28)
        );
    }

    @Test
    void provide_1_year_plus_alpha_months_when_paid_100000_and_extra() {
        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2024, 2, 29))
                .paidAmount(130_000)
                .build(),
            LocalDate.of(2025, 5, 31)
        );

        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2024, 2, 29))
                .paidAmount(40_000)
                .build(),
            LocalDate.of(2024, 6, 30)
        );

        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2024, 1, 31))
                .paidAmount(110_000)
                .build(),
            LocalDate.of(2025, 2, 28)
        );

        assertExpiryDate(
            PayData.builder()
                .billingDate(LocalDate.of(2024, 2, 1))
                .paidAmount(200_000)
                .build(),
            LocalDate.of(2026, 2, 1)
        );
    }

    private void assertExpiryDate(PayData payData, LocalDate expectedExpiryDate) {
        ExpiryDateCalculator cal = new ExpiryDateCalculator();

        LocalDate actualExpiryDate = cal.calculateExpiryDate(payData);

        assertEquals(expectedExpiryDate, actualExpiryDate);
    }
}
