package ch11;

import java.util.ArrayList;
import java.util.List;

public class ArrayListEx2 {
    public static void main(String[] args) {
        final int LIMIT = 10;
        String source = "0123456789zxcvbnmasdfghjklqwertyuiop[;',./";
        int length = source.length();

        List list2 = new ArrayList();
        List list = new ArrayList(length / LIMIT + 10);
        System.out.println(list2.size());;


        for (int i = 0; i < length; i += LIMIT) {
            if (i + LIMIT < length) {
                list.add(source.substring(i, i + LIMIT));
            } else {
                list.add(source.substring(i));
            }
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
