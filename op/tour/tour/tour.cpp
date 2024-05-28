#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <locale.h>

// ��������� ��� ���������� � ����
typedef struct {
    int tourID;             // ������������� ����
    char name[100];          // �������� ����
    char country[50];       // ������, � ������� ���������� ���
    int seatsAvailable;     // ���������� ��������� ���� � ����
    char responsible[50];   // ��� �������������� �� ���
} Tour;

// ��������� ��� �������� ������
typedef struct Node {
    Tour tour;          // ���������� � ����
    struct Node* prev;  // ��������� �� ���������� ������� ������
    struct Node* next;  // ��������� �� ��������� ������� ������
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
Node* createNode(Tour tour) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->tour = tour;
    newNode->prev = NULL;
    newNode->next = NULL;
    return newNode;
}

// ������� �������� � ����� ������
void append(LinkedList* list, Tour tour) {
    Node* newNode = createNode(tour);
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
void prepend(LinkedList* list, Tour tour) {
    Node* newNode = createNode(tour);
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

// ������� �������� � ������ � ��������������� �� �������������� ����
void insert(LinkedList* list, Tour tour) {
    Node* newNode = createNode(tour);
    if (list->head == NULL) {
        list->head = newNode;
        list->tail = newNode;
        return;
    }
    Node* current = list->head;
    while (current != NULL && current->tour.tourID < tour.tourID) {
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
        printf("������������� ����: %d\n", current->tour.tourID);
        printf("�������� ����: %s\n", current->tour.name);
        printf("������: %s\n", current->tour.country);
        printf("��������� �����: %d\n", current->tour.seatsAvailable);
        printf("�������������: %s\n", current->tour.responsible);
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
    setlocale(LC_ALL, "");

    LinkedList list;
    initializeList(&list);

    // ������� ���������� � �����
    Tour tour1 = { 101, "��������� � �����", "�������", 25, "������ ���� ��������" };
    Tour tour2 = { 202, "����� �� ����", "���������", 15, "������ ���� ��������" };
    Tour tour3 = { 303, "����������� ������ � ���������", "���������", 20, "������� ����� ���������" };
    Tour tour4 = { 404, "����� �� ��������", "�������", 10, "������� ����� ��������" };
    Tour tour5 = { 505, "����� �� ������������ ����", "������", 30, "�������� ���� ���������" };

    // ������� ������������� ������� ������� � ������
    insert(&list, tour1);
    insert(&list, tour3);
    insert(&list, tour2);
    insert(&list, tour5);
    insert(&list, tour4);

    // ������� ������������� ������� ������� � ������
    append(&list, tour1);
    prepend(&list, tour2);
    append(&list, tour3);
    prepend(&list, tour4);
    append(&list, tour5);


    // �������� �������� �� ������ (��������, ������� ��������)
    removeNode(&list, list.head);

    // ����� ����������� ������
    displayList(&list);

    // ������������ ������
    freeList(&list);

    return 0;
}