package org.example;

import org.example.model.Person;
import org.example.structure.graph.Graph;
import org.example.structure.hashtable.HashTableChainMethod;
import org.example.structure.hashtable.HashTableDoubleHash;
import org.example.structure.hashtable.HashTableLineProbing;
import org.example.structure.linkedlist.CircularLinkedList;
import org.example.structure.linkedlist.DoubleLinkedList;
import org.example.structure.linkedlist.SingleLinkedList;
import org.example.structure.tree.BinaryHeap;
import org.example.structure.tree.BinaryTree;
import org.example.util.ArrayUtils;
import org.example.util.sort.*;
import java.util.Arrays;

public class Main {

    private static void graph() {
        // Create a new graph
        Graph graph = new Graph();

        // Add vertices
        graph.addVertex(); // Vertex 0
        graph.addVertex(); // Vertex 1
        graph.addVertex(); // Vertex 2
        graph.addVertex(); // Vertex 3
        graph.addVertex(); // Vertex 4

        // Add edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        // Print adjacency list
        System.out.println("Adjacency List:");
        graph.printAdjacencyList();
        System.out.println();

        // Print adjacency matrix
        System.out.println("Adjacency Matrix:");
        graph.printAdjacencyMatrix();
        System.out.println();

        // Print incidence matrix
        System.out.println("Incidence Matrix:");
        graph.printIncidenceMatrix();
    }

    private static void hashTableChainMethod() {
        // Create an instance of HashTableChainMethod with size 10
        HashTableChainMethod hashTable = new HashTableChainMethod(10);

        // Create some Person objects
        Person person1 = Person.builder().name("Alice").age(25).build();
        Person person2 = Person.builder().name("Bob").age(30).build();
        Person person3 = Person.builder().name("Charlie").age(35).build();

        // Insert Person objects into the hash table
        hashTable.insert(person1);
        hashTable.insert(person2);
        hashTable.insert(person3);

        // Print the hash table
        hashTable.print();

        // Search for a Person object
        Person searchPerson = Person.builder().name("Bob").age(30).build();
        boolean isFound = hashTable.find(searchPerson);
        System.out.println("Person found: " + isFound);

        // Delete a Person object
        hashTable.delete(person2);

        // Print the hash table after deletion
        hashTable.print();
    }

    private static void hashTableDoubleHash() {
        // Create a hash table with a size of 10
        HashTableDoubleHash hashTable = new HashTableDoubleHash(10);

        // Create some Person objects
        Person person1 = Person.builder().name("Alice").age(25).build();
        Person person2 = Person.builder().name("Bob").age(30).build();
        Person person3 = Person.builder().name("Charlie").age(35).build();

        // Insert elements into the hash table
        hashTable.insert(person1);
        hashTable.insert(person2);
        hashTable.insert(person3);

        // Print the contents of the hash table
        hashTable.print();

        // Find a person in the hash table
        System.out.println("Is Alice in the hash table? " + hashTable.find(person2));

        // Delete a person from the hash table
        hashTable.delete(person1);

        // Print the updated contents of the hash table
        hashTable.print();
    }

    private static void hashTableLineProbing() {
        // Create an instance of HashTableLineProbing
        HashTableLineProbing hashTable = new HashTableLineProbing(10, 1);

        // Create some PersonModel objects
        Person person1 = Person.builder().name("John").age(25).build();
        Person person2 = Person.builder().name("Alice").age(30).build();

        // Insert persons into the hash table
        hashTable.insert(person1);
        hashTable.insert(person2);

        // Print the hash table
        hashTable.print();

        // Find a person
        boolean found = hashTable.find(person1);
        System.out.println("Person 1 found: " + found);

        // Delete a person
        hashTable.delete(person2);

        // Print the hash table again
        hashTable.print();
    }

    private static void circularLinkedList() {
        CircularLinkedList list = new CircularLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.display(); // Вывод: 1 2 3 4
        list.delete(3);
        list.display(); // Вывод: 1 2 4
        list.clear();
        list.display(); // Вывод: Список пуст
    }

    private static void doubleLinkedList() {
        DoubleLinkedList myList = new DoubleLinkedList();

        myList.add(5);
        myList.add(10);
        myList.add(15);

        System.out.println("Original List:");
        myList.print();

        myList.remove(10);

        System.out.println("List after removing 10:");
        myList.print();

        myList.clear();

        System.out.println("List after clearing:");
        myList.print();
    }

    private static void singleLinkedList() {
        // Instantiate the SingleLinkedList object
        SingleLinkedList list = new SingleLinkedList();

        // Add elements to the list
        list.add(5);
        list.add(10);
        list.add(15);

        // Print the elements of the list
        list.print(); // Output: 5 10 15

        // Remove an element from the list
        list.remove(10);

        // Print the elements after removal
        list.print(); // Output: 5 15

        // Clear the list
        list.clear();

        // Print the elements after clearing
        list.print(); // Output: The list is empty!
    }

    private static void binaryHeap() {
        // Create a BinaryHeap with a maximum size of 10
        BinaryHeap binaryHeap = new BinaryHeap(10);

        // Insert elements into the heap
        binaryHeap.insert(5);
        binaryHeap.insert(10);
        binaryHeap.insert(3);
        binaryHeap.insert(8);
        binaryHeap.insert(1);

        // Print the elements of the heap
        System.out.print("Heap after insertion: ");
        binaryHeap.print(); // Output: 10 8 3 5 1

        // Remove an element at index 0 (root) from the heap
        int removed = binaryHeap.remove(0);
        System.out.println("Removed element: " + removed); // Output: Removed element: 10

        // Print the elements of the heap after removal
        System.out.print("Heap after removal: ");
        binaryHeap.print(); // Output: 8 5 3 1
    }

    private static void binaryTree() {
        BinaryTree tree = new BinaryTree();

        // Добавление элементов
        tree.insert(50);
        tree.insert(30);
        tree.insert(20);
        tree.insert(40);
        tree.insert(70);
        tree.insert(60);
        tree.insert(80);

        // Проверка наличия значений
        System.out.println(tree.search(50)); // true
        System.out.println(tree.search(90)); // false

        // Удаление элемента
        tree.delete(20);

        // Проверка наличия значения после удаления
        System.out.println(tree.search(20)); // false
    }

    private static void sort() {
        int[] orig = ArrayUtils.fillArray(new int[20_000], -100, 100);

        long startTime, endTime;
        Runtime runtime = Runtime.getRuntime();

        System.out.println("Sorting Efficiency:");

        // Bubble Sort
        startTime = System.currentTimeMillis();
        BubbleSortUtils.sort(Arrays.copyOf(orig, orig.length));
        endTime = System.currentTimeMillis();
        System.out.println("Bubble Sort Time: " + (endTime - startTime) + " ns");

        // Counting Sort
        startTime = System.currentTimeMillis();
        CountingSortUtils.sort(Arrays.copyOf(orig, orig.length));
        endTime = System.currentTimeMillis();
        System.out.println("Counting Sort Time: " + (endTime - startTime) + " ns");

        // Gnome Sort
        startTime = System.currentTimeMillis();
        GnomeSortUtils.sort(Arrays.copyOf(orig, orig.length));
        endTime = System.currentTimeMillis();
        System.out.println("Gnome Sort Time: " + (endTime - startTime) + " ns");

        // Merge Sort
        startTime = System.currentTimeMillis();
        MergeSortUtils.sort(Arrays.copyOf(orig, orig.length), 0, orig.length - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Merge Sort Time: " + (endTime - startTime) + " ns");

        // Quick Sort
        startTime = System.currentTimeMillis();
        QuickSortUtils.sort(Arrays.copyOf(orig, orig.length), 0, orig.length - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Quick Sort Time: " + (endTime - startTime) + " ns");

        // Radix Sort
        startTime = System.currentTimeMillis();
        RadixSortUtils.sort(Arrays.copyOf(orig, orig.length));
        endTime = System.currentTimeMillis();
        System.out.println("Radix Sort Time: " + (endTime - startTime) + " ns");

        // Memory Measurement (7560632 b = 7.21 mb)
        long memoryUsed = runtime.totalMemory() - runtime.freeMemory();
        System.out.println("Memory Used: " + memoryUsed + " bytes");
    }

    public static void main(String[] args) {
        hashTableChainMethod();
        hashTableDoubleHash();
        hashTableLineProbing();
        doubleLinkedList();
        singleLinkedList();
        circularLinkedList();
        binaryHeap();
        binaryTree();
        graph();
        sort();
    }
}