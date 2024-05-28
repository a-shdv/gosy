package org.example.util.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Поразрядная сортировка
public class RadixSortUtils {

    /**
     * Сортирует массив методом поразрядной сортировки.
     *
     * @param array Массив, который требуется отсортировать.
     */
    public static void sort(int[] array) {
        int range = 19; // Диапазон значений элементов массива
        int zeroIndex = 9; // Индекс нулевого элемента в списке

        // Находим максимальное и минимальное значения в массиве
        int max = Arrays.stream(array).max().getAsInt();
        int min = Arrays.stream(array).min().getAsInt();

        // Определяем длину наибольшего числа в массиве (в десятичной системе)
        int length;
        if (countOfRadix(max) > countOfRadix(min)) {
            length = countOfRadix(max);
        } else {
            length = countOfRadix(min);
        }

        // Создаем список списков для хранения элементов по разрядам
        List<ArrayList<Integer>> lists = new ArrayList<>();
        for (int i = 0; i < range; i++) {
            lists.add(new ArrayList<Integer>());
        }

        // Проводим сортировку для каждого разряда числа
        for (int i = 0; i < length; ++i) {
            // Распределение элементов по спискам в соответствии с разрядами
            for (int j = 0; j < array.length; j++) {
                int temp = (array[j] % (int) Math.pow(10, i + 1)) / (int) Math.pow(10, i);
                lists.get(temp + zeroIndex).add(array[j]);
            }

            // Сборка отсортированных элементов обратно в массив
            int k = 0;
            for (int j = 0; j < range; j++) {
                for (int a = 0; a < lists.get(j).size(); a++) {
                    array[k++] = lists.get(j).get(a);
                }
            }

            // Очистка списков для следующей итерации
            for (int j = 0; j < range; j++) {
                lists.get(j).clear();
            }
        }
    }

    /**
     * Возвращает количество разрядов числа.
     *
     * @param digit Число, для которого нужно определить количество разрядов.
     * @return Количество разрядов числа.
     */
    private static int countOfRadix(int digit) {
        // Возвращает количество разрядов числа, включая знак
        // Math.log10(100) returns 2.0, because 10^2=100.
        // Math.log10(1000) returns 3.0, because 10^3=1000.
        // Math.log10(1) returns 0.0, because 10^0=1.
        return digit < 0 ? (int) Math.log10(-digit) + 1 : (int) Math.log10(digit) + 1;
    }
}