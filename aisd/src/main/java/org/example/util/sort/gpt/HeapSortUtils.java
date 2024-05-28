package org.example.util.sort.gpt;

public class HeapSortUtils {
    public static void sort(int[] array) {
        int n = array.length;

        // Построение кучи (располагаем массив в виде кучи)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(array, n, i);
        }

        // Один за другим извлекаем элементы из кучи
        for (int i = n - 1; i > 0; i--) {
            // Перемещаем текущий корень в конец
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Вызываем процедуру heapify для уменьшенной кучи
            heapify(array, i, 0);
        }
    }

    private static void heapify(int[] array, int n, int root) {
        int largest = root; // Initialize largest as root
        int left = 2 * root + 1; // left = 2*i + 1
        int right = 2 * root + 2; // right = 2*i + 2

        // If left child is larger than root
        if (left < n && array[left] > array[largest]) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && array[right] > array[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != root) {
            int swap = array[root];
            array[root] = array[largest];
            array[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(array, n, largest);
        }
    }
}