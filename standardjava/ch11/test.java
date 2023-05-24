package ch11;

import java.util.ArrayList;
import java.util.Arrays;

public class test {

    public static void main(String[] args) {
        char[] arr = new char[5];
        Arrays.fill(arr, '*');
        char[] arr2 = {'a', 'b', 'c'};
        int[] arr3 = {48, 65, 97};
        System.out.println(new String(arr));
        System.out.println(new String(arr2));
//        System.out.println(new String(arr3));
    }
}
