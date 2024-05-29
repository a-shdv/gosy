using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ASD;
public class BinaryHeap
{
    private int[] heap;
    private int size;

    public BinaryHeap(int maxSize)
    {
        heap = new int[maxSize];
    }

    public void Insert(int value)
    {
        if (size == heap.Length)
        {
            Console.WriteLine("Heap is full");
            return;
        }

        heap[size] = value;
        size++;

        BubbleUp(size - 1);
    }

    public int Remove(int index)
    {
        if (size == 0)
        {
            Console.WriteLine("Heap is empty");
            return 0;
        }

        int root = heap[index];
        size--;

        heap[index] = heap[size];
        BubbleDown(index);

        return root;
    }

    public void Print()
    {
        for (int i = 0; i < size; i++)
        {
            Console.Write(heap[i] + " ");
        }
        Console.WriteLine();
    }

    private void BubbleUp(int index)
    {
        int parent = (index - 1) / 2;

        if (index > 0 && heap[index] > heap[parent])
        {
            int temp = heap[index];
            heap[index] = heap[parent];
            heap[parent] = temp;

            BubbleUp(parent);
        }
    }

    private void BubbleDown(int index)
    {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int largest = index;

        if (left < size && heap[left] > heap[largest])
        {
            largest = left;
        }

        if (right < size && heap[right] > heap[largest])
        {
            largest = right;
        }

        if (largest != index)
        {
            int temp = heap[index];
            heap[index] = heap[largest];
            heap[largest] = temp;

            BubbleDown(largest);
        }
    }
}


