package org.example.util.sort.gpt;

public class MergeSortUtils {
    public static void sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }

    private static void mergeSort(int[] array, int minIndex, int maxIndex) {
        if (minIndex < maxIndex) {
            int mid = (minIndex + maxIndex) / 2;
            // Рекурсивно сортируем левую и правую половины массива
            mergeSort(array, minIndex, mid);
            mergeSort(array, mid + 1, maxIndex);
            // Объединяем отсортированные половины
            merge(array, minIndex, mid, maxIndex);
        }
    }

    private static void merge(int[] array, int minIndex, int mid, int maxIndex) {
        int[] temp = new int[array.length];
        for (int i = minIndex; i <= maxIndex; i++) {
            temp[i] = array[i];
        }

        int i = minIndex;
        int j = mid + 1;
        int k = minIndex;

        // Слияние двух половин массива в один отсортированный массив
        while (i <= mid && j <= maxIndex) {
            if (temp[i] <= temp[j]) {
                array[k] = temp[i];
                i++;
            } else {
                array[k] = temp[j];
                j++;
            }
            k++;
        }

        // Добавление оставшихся элементов из левой половины, если они есть
        while (i <= mid) {
            array[k] = temp[i];
            k++;
            i++;
        }
    }
}