package ch08;

public class ExceptionEx9 {
    public static void main(String[] args) {
        try {
//            Exception e = new Exception("thrown on purpose.");
//            throw e;
            throw new Exception("thrown on purpose.");
        } catch (Exception e) {
            System.out.println("error message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
