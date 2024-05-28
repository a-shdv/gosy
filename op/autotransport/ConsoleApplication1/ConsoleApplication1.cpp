#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <clocale>

// ��������� ��� ���������� � ����������
typedef struct {
    char licensePlate[20];  // ��������������� �����
    char brand[50];         // �����
    char model[50];         // ������
    int capacity;           // ����������� (���������� ����������)
    int year;               // ��� �������
} Autotransport;

// ��������� ��� �������� ������
typedef struct Node {
    Autotransport transport;    // ���������� � ����������
    struct Node* prev;          // ��������� �� ���������� ������� ������
    struct Node* next;          // ��������� �� ��������� ������� ������
} Node;

// ��������� ��� ������ ������
typedef struct {
    Node* head; // ��������� �� ������ ������� ������
    Node* tail; // ��������� �� ��������� ������� ������
} LinkedList;

// ������������� ������
void initializeList(LinkedList* list) {
    list->head = NULL;
    list->tail = NULL;
}

// �������� ������ �������� ������
Node* createNode(Autotransport transport) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->transport = transport;
    newNode->prev = NULL;
    newNode->next = NULL;
    return newNode;
}

// ������� �������� � ������ � ��������������� �� ���������������� ������
void insert(LinkedList* list, Autotransport transport) {
    Node* newNode = createNode(transport);
    if (list->head == NULL) {
        list->head = newNode;
        list->tail = newNode;
        return;
    }
    Node* current = list->head;
    while (current != NULL && strcmp(current->transport.licensePlate, transport.licensePlate) < 0) {
        current = current->next;
    }
    if (current == NULL) {
        newNode->prev = list->tail;
        list->tail->next = newNode;
        list->tail = newNode;
    }
    else if (current == list->head) {
        newNode->next = list->head;
        list->head->prev = newNode;
        list->head = newNode;
    }
    else {
        if (strcmp(current->transport.licensePlate, transport.licensePlate) == 0) {
            printf("������� � ����� ��������������� ������� ��� ����������\n");
            free(newNode);
            return;
        }
        newNode->next = current;
        newNode->prev = current->prev;
        current->prev->next = newNode;
        current->prev = newNode;
    }
}

// ������� �������� � ����� ������
void append(LinkedList* list, Autotransport transport) {
    Node* newNode = createNode(transport);
    if (list->head == NULL) {
        list->head = newNode;
        list->tail = newNode;
    }
    else {
        newNode->prev = list->tail;
        list->tail->next = newNode;
        list->tail = newNode;
    }
}

// ������� �������� � ������ ������
void prepend(LinkedList* list, Autotransport transport) {
    Node* newNode = createNode(transport);
    if (list->head == NULL) {
        list->head = newNode;
        list->tail = newNode;
    }
    else {
        newNode->next = list->head;
        list->head->prev = newNode;
        list->head = newNode;
    }
}

// �������� �������� �� ������
void removeNode(LinkedList* list, Node* node) {
    if (node == NULL) {
        return;
    }
    if (node->prev != NULL) {
        node->prev->next = node->next;
    }
    else {
        list->head = node->next;
    }
    if (node->next != NULL) {
        node->next->prev = node->prev;
    }
    else {
        list->tail = node->prev;
    }
    free(node);
}

// ����� ������ �� �����
void displayList(LinkedList* list) {
    if (list->head == NULL) {
        printf("������ ����\n");
        return;
    }
    printf("���������� ������:\n");
    Node* current = list->head;
    while (current != NULL) {
        printf("��������������� �����: %s\n", current->transport.licensePlate);
        printf("�����: %s\n", current->transport.brand);
        printf("������: %s\n", current->transport.model);
        printf("�����������: %d\n", current->transport.capacity);
        printf("��� �������: %d\n", current->transport.year);
        printf("\n");
        current = current->next;
    }
}

// ������������ ������, ���������� ��� ������
void freeList(LinkedList* list) {
    Node* current = list->head;
    while (current != NULL) {
        Node* next = current->next;
        free(current);
        current = next;
    }
    list->head = NULL;
    list->tail = NULL;
}

int main() {
    setlocale(LC_ALL, "Russian");

    LinkedList list;
    initializeList(&list);

    // ������� ���������� � ����������
    Autotransport transport1 = { "A123BC", "Toyota", "Camry", 5, 2015 };
    Autotransport transport2 = { "B456DE", "Honda", "Civic", 4, 2018 };
    Autotransport transport3 = { "C789FG", "Ford", "Focus", 4, 2016 };
    Autotransport transport4 = { "B123BV", "Changan", "CS55 PLUS", 5, 2023 };
    Autotransport transport5 = { "G322SD", "Changan", "Uni-T", 4, 2023 };

    // ������� ������������� ������� ������� � ������
    insert(&list, transport1);
    insert(&list, transport2);
    insert(&list, transport3);
    insert(&list, transport4);
    insert(&list, transport5);

    // ����� ����������� ������
    displayList(&list);

    // ������������ ������
    freeList(&list);

    return 0;
}
