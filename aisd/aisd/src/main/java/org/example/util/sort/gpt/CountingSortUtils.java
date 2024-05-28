package org.example.util.sort.gpt;

import java.util.Arrays;

public class CountingSortUtils {
    public static void sort(int[] array) {
        int max = Arrays.stream(array).max().getAsInt();
        int min = Arrays.stream(array).min().getAsInt();

        int range = max - min + 1;
        int[] count = new int[range];

        // Подсчет количества элементов
        for (int i = 0; i < array.length; i++) {
            count[array[i] - min]++;
        }

        // Восстановление отсортированного массива
        int index = 0;
        for (int i = 0; i < range; i++) {
            while (count[i] > 0) {
                array[index++] = i + min;
                count[i]--;
            }
        }
    }
}