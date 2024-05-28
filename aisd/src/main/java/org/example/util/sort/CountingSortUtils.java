package org.example.util.sort;

import java.util.Arrays;

// Cортировка подсчетом описание
public class CountingSortUtils {
    /**
     * Сортирует массив методом сортировки подсчетом.
     *
     * @param array Массив, который требуется отсортировать.
     */
    public static void sort(int[] array) {
        // Поиск минимального и максимального значений в массиве
        int min = Arrays.stream(array).min().getAsInt();
        int max = Arrays.stream(array).max().getAsInt();

        // Корректирующий фактор для обработки отрицательных чисел
        // (используется для сдвига значений в неотрицательную область,
        // чтобы можно было использовать их как индексы в массиве подсчета)
        int correctionFactor = -min;
        max += correctionFactor;

        // Массив для подсчета количества элементов
        int[] count = new int[max + 1];
        for (int i = 0; i < array.length; i++) {
            // Увеличиваем счетчик для каждого элемента массива с учетом коррекции
            count[array[i] + correctionFactor]++;
        }

        // Индекс для восстановления отсортированного массива
        int index = 0;
        for (int i = 0; i < count.length; i++) {
            // Перебираем элементы в массиве подсчета
            for (int j = 0; j < count[i]; j++) {
                // Восстанавливаем отсортированный массив, используя коррекцию
                array[index] = i - correctionFactor;
                index++;
            }
        }
    }
}
