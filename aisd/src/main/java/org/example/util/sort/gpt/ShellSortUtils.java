package org.example.util.sort.gpt;

public class ShellSortUtils {
    public static void sort(int[] array) {
        int n = array.length;

        // Выбираем шаг последовательности Шелла
        for (int gap = n / 2; gap > 0; gap /= 2) {
            // Применяем сортировку вставками с шагом gap
            for (int i = gap; i < n; i++) {
                int temp = array[i];
                int j;
                for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }
    }
}
