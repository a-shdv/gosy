#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <locale.h>

// ��������� ��� ���������� � ��������
typedef struct {
    char registrationNumber[20];   // �������� �����
    char type[50];                 // ��� �������� (������������, ������������ � �.�.)
    char model[50];                // ������
    int cargoCapacity;             // ����������������
    int range;                     // ��������� ������
} Airplane;

// ��������� ��� �������� ������
typedef struct Node {
    Airplane airplane;    // ���������� � ��������
    struct Node* prev;    // ��������� �� ���������� ������� ������
    struct Node* next;    // ��������� �� ��������� ������� ������
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
Node* createNode(Airplane airplane) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->airplane = airplane;
    newNode->prev = NULL;
    newNode->next = NULL;
    return newNode;
}

// ������� �������� � ����� ������
void append(LinkedList* list, Airplane airplane) {
    Node* newNode = createNode(airplane);
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

// ������� �������� � ������ � ��������������� �� ��������� ������
void insert(LinkedList* list, Airplane airplane) {
    Node* newNode = createNode(airplane);
    if (list->head == NULL) {
        list->head = newNode;
        list->tail = newNode;
        return;
    }
    Node* current = list->head;
    while (current != NULL && strcmp(current->airplane.registrationNumber, airplane.registrationNumber) < 0) {
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
        newNode->next = current;
        newNode->prev = current->prev;
        current->prev->next = newNode;
        current->prev = newNode;
    }
}

// ������� �������� � ������ ������
void prepend(LinkedList* list, Airplane airplane) {
    Node* newNode = createNode(airplane);
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
        printf("�������� �����: %s\n", current->airplane.registrationNumber);
        printf("���: %s\n", current->airplane.type);
        printf("������: %s\n", current->airplane.model);
        printf("����������������: %d\n", current
            ->airplane.cargoCapacity);
        printf("��������� ������: %d\n", current->airplane.range);
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

    // ������� ���������� � ���������
    Airplane airplane1 = { "SU123", "������������", "Airbus A320", 50000, 5000 };
    Airplane airplane2 = { "BA456", "��������", "Boeing 747", 200000, 10000 };
    Airplane airplane3 = { "AF789", "������������", "Airbus A380", 80000, 7000 };
    Airplane airplane4 = { "LH246", "������������", "Boeing 737", 60000, 6000 };
    Airplane airplane5 = { "EK357", "��������", "Antonov An-124", 150000, 8000 };

    // ������� ������������� ������� ������� � ������
    insert(&list, airplane1);
    insert(&list, airplane3);
    insert(&list, airplane2);
    insert(&list, airplane5);
    insert(&list, airplane4);

    // ������� ������������� ������� ������� � ������
    append(&list, airplane1);
    prepend(&list, airplane2);
    append(&list, airplane3);
    prepend(&list, airplane4);
    append(&list, airplane5);

    // �������� �������� �� ������ (��������, ������� ��������)
    removeNode(&list, list.head);

    // ����� ����������� ������
    displayList(&list);

    // ������������ ������
    freeList(&list);

    return 0;
}
