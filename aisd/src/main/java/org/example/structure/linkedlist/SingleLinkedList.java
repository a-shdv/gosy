package org.example.structure.linkedlist;

import lombok.Getter;
import lombok.Setter;

// Однонаправленный связный список
public class SingleLinkedList {

    // Class representing a node in the singly linked list
    @Getter
    @Setter
    static class SingleLinkedListNode {
        private int data;
        private SingleLinkedListNode next;

        // Constructor
        public SingleLinkedListNode(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private SingleLinkedListNode root;

    // Method to add a new node with the given value to the end of the list
    public void add(int value) {
        if (root == null) {
            // If the list is empty, create a new node and set it as the root
            root = new SingleLinkedListNode(value);
        } else {
            // Traverse the list to find the last node
            SingleLinkedListNode item = root;
            while (item.getNext() != null) {
                item = item.getNext();
            }
            // Create a new node with the given value and link it to the last node
            item.setNext(new SingleLinkedListNode(value));
        }
    }

    // Method to print the elements of the list
    public void print() {
        System.out.println("Elements of the list:");
        if (root == null) {
            System.out.println("The list is empty!");
        } else {
            SingleLinkedListNode item = root;
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
                root = root.getNext();
                return;
            }

            SingleLinkedListNode item = root;
            while (item.getNext() != null) {
                if (item.getNext().getData() == value) {
                    // If the next node contains the value
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
