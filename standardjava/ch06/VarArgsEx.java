package ch06;

public class VarArgsEx {
    public static void main(String[] args) {
        String[] strArr = {"aaa", "bbb", "ccc"};

        System.out.println(concatenate("", "aaa", "bbb", "ccc"));
        System.out.println(concatenate("-", strArr));
        System.out.println(concatenate(",", new String[]{"x", "y", "z"}));
        System.out.println("[" + concatenate(",", new String[0]) + "]");
        System.out.println("[" + concatenate(",") + "]");
    }

    static String concatenate(String delim, String... args) {
        String result = "";

        for (String str : args) {
            result += str + delim;
        }

        return result;
    }

    /*
    static String concatenate(String... args) {
        return concatenate("", args);
    }
    */

}
