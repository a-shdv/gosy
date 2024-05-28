package org.example.util.sort;

// Пузырьковая сортировка
public class BubbleSortUtils {
    /**
     * Сортирует массив методом пузырьковой сортировки.
     *
     * @param array Массив, который требуется отсортировать.
     */
    public static void sort(int[] array) {
        // Внешний цикл для прохода по всем элементам массива
        for (int i = 0; i < array.length; i++) {
            // Внутренний цикл для сравнения и перестановки элементов
            for (int j = 0; j < array.length - 1; j++) {
                // Если текущий элемент больше следующего, меняем их местами
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
