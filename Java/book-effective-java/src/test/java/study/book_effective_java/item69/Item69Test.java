package study.book_effective_java.item69;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

public class Item69Test {

    final static int SIZE = 100; // 배열 사이즈 설정
    final int REPEAT_COUNT = 1_000_000;  // 반복문 * 100회 반복

    @Test
    void iterateMultipleTimes_Then_Exception() {
        boolean[] array = new boolean[SIZE];

        long startTime = System.currentTimeMillis();
        for (int j = 0; j < REPEAT_COUNT; j++) {
            try {
                int i = 0;

                while (true) {
                    array[i++] = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");
    }


    @Test
    void iterateMultipleTimes_With_Standard_Loop() {
        boolean[] array = new boolean[SIZE];

        long startTime = System.currentTimeMillis();
        for (int j = 0; j < REPEAT_COUNT; j++) {
            for (int i = 0; i < SIZE; i++) {
                array[i] = true;
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");
    }

    @Test
    void iterateWithIterator_Then_Exception() {
        Collection<Boolean> collection = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            collection.add(false);
        }

        long startTime = System.currentTimeMillis();
        for (int j = 0; j < REPEAT_COUNT; j++) {
            try {
                Iterator<Boolean> i = collection.iterator();
                while (true) {
                    Boolean bool = i.next();
                }
            } catch (NoSuchElementException e) {
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");
    }
    @Test
    void iterateWithIterator() {
        Collection<Boolean> collection = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            collection.add(false);
        }

        long startTime = System.currentTimeMillis();
        for (int j = 0; j < REPEAT_COUNT; j++) {
            for (Iterator<Boolean> i = collection.iterator(); i.hasNext(); ) {
                boolean bool = i.next();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Execution time: " + (endTime - startTime) + " milliseconds");
    }
}
