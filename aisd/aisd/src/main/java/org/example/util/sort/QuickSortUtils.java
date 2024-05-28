package org.example.util.sort;

// Быстрая сортировка
public class QuickSortUtils {

    /**
     * Выполняет разделение массива на две части относительно опорного элемента.
     *
     * @param array    Массив, который нужно разделить.
     * @param minIndex Начальный индекс массива.
     * @param maxIndex Конечный индекс массива.
     * @return Индекс опорного элемента после разделения.
     */
    private static int partition(int[] array, int minIndex, int maxIndex) {
        int pivotIndex = minIndex; // Инициализируем индекс опорного элемента значением minIndex
        int pivotValue = array[maxIndex]; // Выбираем последний элемент в качестве опорного
        int temp;

        for (int i = minIndex; i < maxIndex; i++) {
            if (array[i] < pivotValue) {
                // Меняем местами array[i] и array[pivotIndex]
                temp = array[i];
                array[i] = array[pivotIndex];
                array[pivotIndex] = temp;
                pivotIndex++;
            }
        }

        // Меняем местами array[maxIndex] (опорный элемент) и array[pivotIndex]
        temp = array[maxIndex];
        array[maxIndex] = array[pivotIndex];
        array[pivotIndex] = temp;

        return pivotIndex;
    }

    /**
     * Рекурсивно сортирует подмассивы относительно опорного элемента.
     *
     * @param array    Массив для сортировки.
     * @param minIndex Начальный индекс массива.
     * @param maxIndex Конечный индекс массива.
     */
    public static void sort(int[] array, int minIndex, int maxIndex) {
        if (minIndex < maxIndex) {
            int pivot = partition(array, minIndex, maxIndex);
            // Рекурсивно сортируем левую часть относительно опорного элемента
            sort(array, minIndex, pivot - 1);
            // Рекурсивно сортируем правую часть относительно опорного элемента
            sort(array, pivot + 1, maxIndex);
        }
    }
}