using System;
using System.Collections;

namespace ASD;
public class Sorts
{
    private readonly Random _random = new Random();
    public int[] FillArray(int size)
    {
        return Enumerable.Range(0, 20).Select(x => _random.Next(-100, 100)).ToArray();
    }

    public void PrintArray(int[] array)
    {
        foreach (var element in array)
        {
            Console.Write("->" + element);
        }
        Console.WriteLine();
    }

    //начало быстрой сортировки

    private int Partition(int[] array, int minIndex, int maxIndex)
    {
        int pivotIndex = minIndex - 1;
        var temp = 0;

        for (int i = minIndex; i < maxIndex; i++)
        {
            if (array[i] < array[maxIndex])
            {
                pivotIndex++;
                temp = array[i];
                array[i] = array[pivotIndex];
                array[pivotIndex] = temp;
            }
        }
        pivotIndex++;
        temp = array[maxIndex];
        array[maxIndex] = array[pivotIndex];
        array[pivotIndex] = temp;

        return pivotIndex;
    }

    public void QuickSort(int[] array, int minIndex, int maxIndex)
    {
        if (minIndex < maxIndex)
        {
            int pivot = Partition(array, minIndex, maxIndex);
            QuickSort(array, minIndex, pivot - 1);
            QuickSort(array, pivot + 1, maxIndex);
        }
    }

    //конец быстрой сортировки

    //начало сортировки слиянием 

    public void MergeSort(int[] array, int minIndex, int maxIndex)
    {
        if (minIndex < maxIndex)
        {
            var middleIndex = (minIndex + maxIndex) / 2;
            MergeSort(array, minIndex, middleIndex);
            MergeSort(array, middleIndex + 1, maxIndex);
            Merge(array, minIndex, middleIndex, maxIndex);
        }
    }

    private void Merge(int[] array, int minIndex, int middleIndex, int maxIndex)
    {
        var left = minIndex;
        var right = middleIndex + 1;
        var tempArray = new int[maxIndex - minIndex + 1];
        var index = 0;

        while ((left <= middleIndex) && (right <= maxIndex))
        {
            if (array[left] < array[right])
            {
                tempArray[index] = array[left];
                left++;
            }
            else
            {
                tempArray[index] = array[right];
                right++;
            }

            index++;
        }

        for (var i = left; i <= middleIndex; i++)
        {
            tempArray[index] = array[i];
            index++;
        }

        for (var i = right; i <= maxIndex; i++)
        {
            tempArray[index] = array[i];
            index++;
        }

        for (var i = 0; i < tempArray.Length; i++)
        {
            array[minIndex + i] = tempArray[i];
        }
    }

    //конец сортировки слиянием

    //начало поразрядной сортировки

    public void RadixSort(int[] array)
    {
        var range = 19;
        var zeroIndex = 9;

        var max = array.Max();
        var min = array.Min();

        int length;

        if (CountOfRadix(max) > CountOfRadix(min))
        {
            length = CountOfRadix(max);
        }
        else
        {
            length = CountOfRadix(min);
        }

        var lists = new List<List<int>>();

        for (int i = 0; i < range; i++)
        {
            lists.Add(new List<int>());
        }


        for (int i = 0; i < length; ++i)
        {
            //распределение по спискам
            for (int j = 0; j < array.Length; j++)
            {
                int temp = (array[j] % (int)Math.Pow(10, i + 1)) / (int)Math.Pow(10, i);
                lists[temp + zeroIndex].Add(array[j]);
            }

            //сборка
            int k = 0;
            for (int j = 0; j < range; j++)
            {
                for (int a = 0; a < lists[j].Count; a++)
                {
                    array[k++] = (int)lists[j][a];
                }
            }

            for (int j = 0; j < range; j++)
            {
                lists[j].Clear();
            }
        }
    }

    private int CountOfRadix(int digit)
    {
        if (digit < 0)
        {
            return digit.ToString().Length - 1;
        }
        else
        {
            return digit.ToString().Length;
        }
    }
    //конец поразрядной сортировки

    //начало сортировки подсчетом

    public void CountingSort(int[] array)
    {
        //поиск минимального и максимального значений
        var min = array.Min();
        var max = array.Max();

        //поправка
        var correctionFactor = -min;
        max += correctionFactor;

        int[] count = new int[max + 1];
        for (int i = 0; i < array.Length; i++)
        {
            count[array[i] + correctionFactor]++;
        }

        int index = 0;
        for (int i = 0; i < count.Length; i++)
        {
            for (int j = 0; j < count[i]; j++)
            {
                array[index] = i - correctionFactor;
                index++;
            }
        }
    }

    //конец сортировки подсчетом

    // начало сортировки пузырьком

    public void BubbleSort(int[] array)
    {
        for (int i = 0; i < array.Length; i++)
        {
            for (int j = 0; j < array.Length - 1; j++)
            {
                if (array[j] > array[j + 1])
                {
                    var temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    //конец сортировки пузырьком

    //начало гномьей сортировки

    public void GnomeSort(int[] array)
    {
        int i = 1;
        int j = 2;
        while (i < array.Length)
        {
            if (array[i - 1] < array[i])
            {
                i = j;
                j += 1;
            }
            else
            {
                var temp = array[i];
                array[i] = array[i - 1];
                array[i - 1] = temp;
                i -= 1;
                if (i == 0)
                {
                    i = j;
                    j += 1;
                }
            }
        }
    }

    //конец гномьей сортировки
}
