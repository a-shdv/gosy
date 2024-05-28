package org.example.util.sort.gpt;

public class GnomeSortUtils {
    public static void sort(int[] array) {
        int i = 1;
        while (i < array.length) {
            if (i == 0 || array[i - 1] <= array[i]) {
                // Если текущий элемент больше предыдущего или находимся в начале массива, продвигаемся вперед
                i++;
            } else {
                // Если текущий элемент меньше предыдущего, меняем их местами и двигаемся назад
                int temp = array[i];
                array[i] = array[i - 1];
                array[i - 1] = temp;
                i--;
            }
        }
    }
}