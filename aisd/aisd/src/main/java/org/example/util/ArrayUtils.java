package org.example.util;

import java.util.Arrays;
import java.util.Random;

public class ArrayUtils {
    private static final Random random = new Random();

    public static int[] fillArray(int[] array, int min, int max) {
        return Arrays.stream(array).map(el -> random.nextInt(max - min + 1) + min).toArray();
    }

    public static void printArray(int[] array) {
        Arrays.stream(array).forEach(el -> System.out.print(el + " "));
        System.out.println();
    }
}
