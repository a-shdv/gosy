package org.example.structure.tree;

// Бинарная куча
public class BinaryHeap {
    private int[] heap; // Array to store the heap elements
    private int size;   // Current size of the heap

    // Constructor to initialize the BinaryHeap with maximum size
    public BinaryHeap(int maxSize) {
        heap = new int[maxSize];
    }

    // Method to insert a new element into the heap
    public void insert(int value) {
        // Check if the heap is full
        if (size == heap.length) {
            System.out.println("Heap is full");
            return;
        }

        // Insert the value at the end of the heap array
        heap[size] = value;
        size++;

        // Perform bubble-up operation to maintain heap property
        bubbleUp(size - 1);
    }

    // Method to remove an element at a specified index from the heap
    public int remove(int index) {
        // Check if the heap is empty
        if (size == 0) {
            System.out.println("Heap is empty");
            return 0;
        }

        // Store the value of the root element to be removed
        int root = heap[index];
        size--;

        // Replace the removed element with the last element in the heap
        heap[index] = heap[size];

        // Perform bubble-down operation to maintain heap property
        bubbleDown(index);

        return root;
    }

    // Method to print the elements of the heap
    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    // Helper method to perform the bubble-up operation
    private void bubbleUp(int index) {
        int parent = (index - 1) / 2;

        // Swap the element with its parent if it violates the heap property
        if (index > 0 && heap[index] > heap[parent]) {
            int temp = heap[index];
            heap[index] = heap[parent];
            heap[parent] = temp;

            // Recursively perform bubble-up operation on the parent
            bubbleUp(parent);
        }
    }

    // Helper method to perform the bubble-down operation
    private void bubbleDown(int index) {
        int left = index * 2 + 1;
        int right = index * 2 + 2;
        int largest = index;

        // Find the index of the largest child element
        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }

        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }

        // Swap the element with its largest child if it violates the heap property
        if (largest != index) {
            int temp = heap[index];
            heap[index] = heap[largest];
            heap[largest] = temp;

            // Recursively perform bubble-down operation on the largest child
            bubbleDown(largest);
        }
    }
}

