package org.example.util.sort.gpt;

public class BottomUpMergeSortUtils {
    public static void sort(int[] array) {
        int n = array.length;
        int[] temp = new int[n];
        for (int size = 1; size < n; size *= 2) {
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * size) {
                int mid = leftStart + size - 1;
                int rightEnd = Math.min(leftStart + 2 * size - 1, n - 1);
                merge(array, temp, leftStart, mid, rightEnd);
            }
        }
    }

    private static void merge(int[] array, int[] temp, int leftStart, int mid, int rightEnd) {
        int leftEnd = mid;
        int rightStart = mid + 1;
        int size = rightEnd - leftStart + 1;

        int left = leftStart;
        int right = rightStart;
        int index = leftStart;

        while (left <= leftEnd && right <= rightEnd) {
            if (array[left] <= array[right]) {
                temp[index++] = array[left++];
            } else {
                temp[index++] = array[right++];
            }
        }

        while (left <= leftEnd) {
            temp[index++] = array[left++];
        }
        while (right <= rightEnd) {
            temp[index++] = array[right++];
        }

        for (int i = leftStart; i <= rightEnd; i++) {
            array[i] = temp[i];
        }
    }
}
