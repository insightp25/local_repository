import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StringCalculator {

    boolean isInteger(String strValue) {
        try {
            Integer.parseInt(strValue);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    int getValue() {
        Scanner scanner = new Scanner(System.in);

//        String value = "5 + 1 * 100 - 3";
        String value = scanner.nextLine();
        String[] array = value.split(" ");
        List<Integer> intArr = new ArrayList<>();
        List<String> operatorArr = new ArrayList<>();

        for (String e : array){
            if (isInteger(e)) {
                intArr.add(Integer.parseInt(e));
            } else {
                operatorArr.add(e);
            }
        }
        int answer = intArr.get(0);


//        for (int i : intArr) {
//            System.out.println(i);
//        }


        for (int i = 0; i < intArr.size() - 1; i++) {
            String s = operatorArr.get(i);
            if (s.equals("+")) {
                answer += intArr.get((i + 1));
            } else if (s.equals("-")) {
                answer -= intArr.get((i + 1));
            } else if (s.equals("*")) {
                answer *= intArr.get(i + 1);
            } else if (s.equals("/")) {
                answer /= intArr.get(i + 1);
            }

        }

        System.out.print(answer);
        return answer;
    }

    public static void main(String[] args) {
        StringCalculator strCalc = new StringCalculator();

        strCalc.getValue();
    }

}
