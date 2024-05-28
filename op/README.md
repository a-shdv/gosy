Собственная реализация списков и массивов
```c++
#include <iostream>

struct Plane {
    int number;
    const char* type;
    const char* model;
    int gruz;
    int dalnost;

    Plane(int _number, const char* _type, const char* _model, int _gruz, int _dalnost) {
        number = _number;
        type = _type;
        model = _model;
        gruz = _gruz;
        dalnost = _dalnost;
    }
};

class Array {
private:
    Plane** data;
    int capacity;
    int size;

public:
    Array(int capacity) {
        this->capacity = capacity;
        size = 0;
        data = new Plane * [capacity];
    }

    ~Array() {
        delete[] data;
    }

    void add(Plane* plane) {
        if (size < capacity) {
            data[size++] = plane;
        }
        else {
            std::cout << "Array is full" << std::endl;
        }
    }

    void print() {
        std::cout << "Array of planes:" << std::endl;
        for (int i = 0; i < size; i++) {
            std::cout << "Element " << i + 1 << " - number: " << data[i]->number
                << ", type: " << data[i]->type
                << ", model: " << data[i]->model
                << ", gruz: " << data[i]->gruz
                << ", dalnost: " << data[i]->dalnost << std::endl;
        }
    }
};

struct ListNode {
    Plane* object;
    ListNode* next;

    ListNode(Plane* value) {
        object = value;
        next = nullptr;
    }
};

class List {
private:
    ListNode* head;

public:
    List() {
        head = nullptr;
    }

    void add(Plane* plane) {
        ListNode* newNode = new ListNode(plane);
        if (head == nullptr) {
            head = newNode;
        }
        else {
            ListNode* temp = head;
            while (temp->next != nullptr) {
                temp = temp->next;
            }
            temp->next = newNode;
        }
    }

    void print() {
        std::cout << "List of planes:" << std::endl;
        ListNode* temp = head;
        int i = 1;
        while (temp != nullptr) {
            std::cout << "Element " << i++ << " - number: " << temp->object->number
                << ", type: " << temp->object->type
                << ", model: " << temp->object->model
                << ", gruz: " << temp->object->gruz
                << ", dalnost: " << temp->object->dalnost << std::endl;
            temp = temp->next;
        }
    }
};

// Реализация на оценку "4": односвязный список
class SinglyLinkedList {
private:
    ListNode* head;

public:
    SinglyLinkedList() {
        head = nullptr;
    }

    void add(Plane* value) {
        ListNode* new_node = new ListNode(value);
        if (!head || head->object->number > value->number) {
            new_node->next = head;
            head = new_node;
            return;
        }
        ListNode* current = head;
        while (current->next && current->next->object->number <= value->number) {
            current = current->next;
        }
        new_node->next = current->next;
        current->next = new_node;
    }

    void print_list() {
        ListNode* current = head;
        int i = 1;
        std::cout << "\nSingly Linked List:" << std::endl;
        while (current) {
            std::cout << "Element " << i++ << " - number: " << current->object->number
                << ", type: " << current->object->type
                << ", model: " << current->object->model
                << ", gruz: " << current->object->gruz
                << ", dalnost: " << current->object->dalnost << std::endl;
            current = current->next;
        }
    }
};

// Реализация на оценку "5": двусвязный список
class DoublyLinkedList {
private:
    struct Node {
        Plane* object;
        Node* next;
        Node* prev;

        Node(Plane* value) {
            object = value;
            next = nullptr;
            prev = nullptr;
        }
    };

    Node* head;
    Node* tail;

public:
    DoublyLinkedList() {
        head = nullptr;
        tail = nullptr;
    }

    void add(Plane* value) {
        Node* new_node = new Node(value);
        if (is_empty()) {
            head = new_node;
            tail = new_node;
        }
        else {
            Node* prevNode = nullptr;
            Node* currentNode = head;

            if (currentNode->object->number > value->number) {
                new_node->next = currentNode;
                new_node->prev = prevNode;
                head = new_node;
                currentNode->prev = new_node;
                return;
            }

            prevNode = currentNode;
            currentNode = currentNode->next;

            while (currentNode != nullptr) {
                if (currentNode->object->number > value->number) {
                    new_node->next = currentNode;
                    new_node->prev = prevNode;
                    currentNode->prev = new_node;
                    prevNode->next = new_node;
                    return;
                }
                else {
                    prevNode = currentNode;
                    currentNode = currentNode->next;
                }
            }

            new_node->next = currentNode;
            new_node->prev = prevNode;
            tail = new_node;
            prevNode->next = new_node;
        }
    }

    void print_list() {
        if (is_empty()) {
            std::cout << "List is empty" << std::endl;
            return;
        }

        Node* currentNode = head;
        int i = 1;
        std::cout << "\nDoubly Linked List:" << std::endl;
        while (currentNode != nullptr) {
            std::cout << "Element " << i++ << " - number: " << currentNode->object->number
                << ", type: " << currentNode->object->type
                << ", model: " << currentNode->object->model
                << ", gruz: " << currentNode->object->gruz
                << ", dalnost: " << currentNode->object->dalnost << std::endl;
            currentNode = currentNode->next;
        }
    }

    bool is_empty() {
        return head == nullptr && tail == nullptr;
    }
};

int main() {
    // Оценка "3": Собственный массив и список
    Array myArray(4);
    myArray.add(new Plane(5, "pass", "11", 10, 100));
    myArray.add(new Plane(4, "gruz", "22", 10, 200));
    myArray.add(new Plane(23, "test", "33", 10, 300));
    myArray.add(new Plane(71, "test", "44", 10, 400));
    myArray.print();

    List myList;
    myList.add(new Plane(5, "pass", "11", 10, 100));
    myList.add(new Plane(4, "gruz", "22", 10, 200));
    myList.add(new Plane(23, "test", "33", 10, 300));
    myList.add(new Plane(71, "test", "44", 10, 400));
    myList.print();

    // Оценка "4": Односвязный список
    SinglyLinkedList singlyList;
    singlyList.add(new Plane(5, "pass", "11", 10, 100));
    singlyList.add(new Plane(4, "gruz", "22", 10, 200));
    singlyList.add(new Plane(23, "test", "33", 10, 300));
    singlyList.add(new Plane(71, "test", "44", 10, 400));
    singlyList.print_list();

    // Оценка "5": Двусвязный список
    DoublyLinkedList doublyList;
    doublyList.add(new Plane(5, "pass", "11", 10, 100));
    doublyList.add(new Plane(4, "gruz", "22", 10, 200));
    doublyList.add(new Plane(23, "test", "33", 10, 300));
    doublyList.add(new Plane(71, "test", "44", 10, 400));
    doublyList.print_list();

    return 0;
}
```