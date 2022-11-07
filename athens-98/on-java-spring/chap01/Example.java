import java.util.ArrayList;
import java.util.HashMap;

public class Example {

    public static void main(String[] args) {

        class BankAccount {
            int bankCode;
            private int password = 9999;

            void inquiry() {};

            BankAccount() {
            }

            BankAccount(int bankCode, int password) {
                this.bankCode = bankCode;
                this.password = password;
            }
        }

        BankAccount ba = new BankAccount();
        System.out.println(ba.bankCode);

        BankAccount ba2 = new BankAccount();
        System.out.println(ba2.password);

    }
}
