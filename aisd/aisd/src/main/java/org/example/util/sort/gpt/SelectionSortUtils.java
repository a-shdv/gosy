package org.example.util.sort.gpt;

public class SelectionSortUtils {
    public static void sort(int[] array) {
        int n = array.length;
        // Просматриваем весь массив
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            // Находим индекс минимального элемента в оставшейся части массива
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            // Меняем местами текущий элемент и минимальный элемент
            if (minIndex != i) {
                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }
}
