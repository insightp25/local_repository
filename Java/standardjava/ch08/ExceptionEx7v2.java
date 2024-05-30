package ch08;

public class ExceptionEx7v2 {
    public static void main(String[] args) {
        System.out.println(1);
        System.out.println(2);
        try {
            System.out.println(3);
            System.out.println(0 / 0);
            System.out.println(4);
        } catch (ArithmeticException | NullPointerException ae) {
            if (ae instanceof ArithmeticException) {
                ArithmeticException e1 = (ArithmeticException)ae;
                e1.printStackTrace();
                System.out.println("true");
                System.out.println("ArithmeticException");
            } else {
                NullPointerException e1 = (NullPointerException)ae;
                e1.printStackTrace();
                System.out.println("NullPointerException");
            }
        }
        System.out.println(6);
    }
}
