public class FizzBuzz {
    public static String evaluate(int number) {
        if (number % 15 == 0) {
            return "FizzBuzz";
        } else if (number % 3 == 0) {
            return "Fizz";
        } else if (number % 5 == 0) {
            return "Buzz";
        }

        return Integer.toString(number);
    }

    public static String[] generate(int start, int end) {
        String[] results = new String[end - start + 1];

        for (int i = start; i <= end; i++) {
            results[i - start] = evaluate(i);
        }

        return results;
    }
}
