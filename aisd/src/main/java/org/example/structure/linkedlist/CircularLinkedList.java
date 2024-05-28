package org.example.structure.linkedlist;

public class CircularLinkedList {

    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }


    private Node head;
    private int size;

    public CircularLinkedList() {
        head = null;
        size = 0;
    }

    // Добавление элемента в конец списка
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            newNode.next = head;
        } else {
            Node temp = head;
            while (temp.next != head) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.next = head;
        }
        size++;
    }

    // Удаление элемента по значению
    public void delete(int data) {
        if (head == null) {
            return;
        }

        Node current = head;
        Node prev = null;

        do {
            if (current.data == data) {
                if (prev == null) {
                    head = current.next;
                    Node temp = head;
                    while (temp.next != current) {
                        temp = temp.next;
                    }
                    temp.next = head;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        } while (current != head);
    }

    // Очистка списка
    public void clear() {
        head = null;
        size = 0;
    }

    // Вывод информации о списке
    public void display() {
        if (head == null) {
            System.out.println("Список пуст");
            return;
        }
        Node current = head;
        do {
            System.out.print(current.data + " ");
            current = current.next;
        } while (current != head);
        System.out.println();
    }

    // Получение размера списка
    public int size() {
        return size;
    }
}
