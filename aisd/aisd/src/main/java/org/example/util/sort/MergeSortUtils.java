package org.example.util.sort;

// Cортировка слиянием
public class MergeSortUtils {

    /**
     * Объединяет два отсортированных подмассива в один отсортированный массив.
     *
     * @param array       Массив, содержащий подмассивы для объединения.
     * @param minIndex    Начальный индекс первого подмассива.
     * @param middleIndex Конечный индекс первого подмассива (также является конечным индексом первого подмассива).
     * @param maxIndex    Конечный индекс второго подмассива.
     */
    private static void merge(int[] array, int minIndex, int middleIndex, int maxIndex) {
        int left = minIndex; // Индекс начала первого подмассива
        int right = middleIndex + 1; // Индекс начала второго подмассива
        int[] tempArray = new int[maxIndex - minIndex + 1]; // Временный массив для объединения подмассивов
        int index = 0; // Индекс временного массива

        // Пока оба подмассива не исчерпаны
        while ((left <= middleIndex) && (right <= maxIndex)) {
            // Сравниваем элементы в началах подмассивов и добавляем меньший во временный массив
            if (array[left] < array[right]) {
                tempArray[index] = array[left];
                left++;
            } else {
                tempArray[index] = array[right];
                right++;
            }
            index++;
        }

        // Если в первом подмассиве остались элементы, добавляем их в конец временного массива
        for (int i = left; i <= middleIndex; i++) {
            tempArray[index] = array[i];
            index++;
        }

        // Если во втором подмассиве остались элементы, добавляем их в конец временного массива
        for (int i = right; i <= maxIndex; i++) {
            tempArray[index] = array[i];
            index++;
        }

        // Копируем временный массив обратно в основной массив
        for (int i = 0; i < tempArray.length; i++) {
            array[minIndex + i] = tempArray[i];
        }
    }

    /**
     * Рекурсивно сортирует подмассивы и объединяет их.
     *
     * @param array    Массив для сортировки.
     * @param minIndex Начальный индекс массива.
     * @param maxIndex Конечный индекс массива.
     */
    public static void sort(int[] array, int minIndex, int maxIndex) {
        if (minIndex < maxIndex) {
            // Находим середину массива
            int middleIndex = (minIndex + maxIndex) / 2;
            // Сортируем левую половину
            sort(array, minIndex, middleIndex);
            // Сортируем правую половину
            sort(array, middleIndex + 1, maxIndex);
            // Объединяем отсортированные подмассивы
            merge(array, minIndex, middleIndex, maxIndex);
        }
    }
}
