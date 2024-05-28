package org.example.util.sort.gpt;

public class InsertionSortUtils {
    public static void sort(int[] array) {
        int n = array.length;
        // Поочередно рассматриваем каждый элемент массива
        for (int i = 1; i < n; i++) {
            int key = array[i];
            int j = i - 1;
            // Перемещаем элементы большие, чем key, на одну позицию вперед
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            // Вставляем key в правильную позицию
            array[j + 1] = key;
        }
    }
}
