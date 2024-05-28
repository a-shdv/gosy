package org.example.structure.linkedlist;

import lombok.Getter;
import lombok.Setter;

// Двунаправленный связный список
public class DoubleLinkedList {
    // Class representing a node in the doubly linked list
    @Getter
    @Setter
    static class DoubleLinkedListNode {
        // Getter and setter methods for data, next, and prev
        private int data;
        private DoubleLinkedListNode next;
        private DoubleLinkedListNode prev;

        // Constructor
        public DoubleLinkedListNode(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }


    private DoubleLinkedListNode root;

    // Method to add a new node with the given value to the end of the list
    public void add(int value) {
        if (root == null) {
            // If the list is empty, create a new node and set it as the root
            root = new DoubleLinkedListNode(value);
        } else {
            // Traverse the list to find the last node
            DoubleLinkedListNode item = root;
            while (item.getNext() != null) {
                item = item.getNext();
            }
            // Create a new node with the given value and link it to the last node
            DoubleLinkedListNode newNode = new DoubleLinkedListNode(value);
            newNode.setPrev(item);
            item.setNext(newNode);
        }
    }

    // Method to print the elements of the list
    public void print() {
        System.out.println("Elements of the list:");
        if (root == null) {
            System.out.println("The list is empty!");
        } else {
            DoubleLinkedListNode item = root;
            while (item != null) {
                System.out.print(item.getData() + " ");
                item = item.getNext();
            }
            System.out.println();
        }
    }

    // Method to remove the node containing the given value from the list
    public void remove(int value) {
        if (root == null) {
            return;
        } else {
            if (root.getData() == value) {
                // If the root node contains the value
                if (root.getNext() == null) {
                    root = null; // If it's the only node in the list
                } else {
                    root.getNext().setPrev(null);
                    root = root.getNext();
                }
                return;
            }

            DoubleLinkedListNode item = root;
            while (item.getNext() != null) {
                if (item.getNext().getData() == value) {
                    // If the next node contains the value
                    if (item.getNext().getNext() != null) {
                        item.getNext().getNext().setPrev(item);
                    }
                    item.setNext(item.getNext().getNext());
                    return;
                }
                item = item.getNext();
            }
        }
    }

    // Method to clear the list
    public void clear() {
        root = null;
    }
}