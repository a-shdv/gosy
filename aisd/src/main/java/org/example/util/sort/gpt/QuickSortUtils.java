package org.example.util.sort.gpt;

public class QuickSortUtils {
    public static void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivot = partition(array, low, high);
            // Рекурсивно сортируем элементы до и после опорного элемента
            quickSort(array, low, pivot - 1);
            quickSort(array, pivot + 1, high);
        }
    }

    private static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;
                // Меняем элементы местами
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        // Меняем опорный элемент местами с элементом, стоящим за всеми элементами, меньшими опорного
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }
}
