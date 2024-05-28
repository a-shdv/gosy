#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <locale.h>

// ��������� ��� ���������� � �������������
typedef struct {
    int departmentNumber;       // ����� �������������
    char name[50];              // ��������
    char specialization[50];    // �������������
    int employeeCount;          // ���������� �����������
    char managerName[50];       // ��� ������������
} Department;

// ��������� ��� �������� ������
typedef struct Node {
    Department department;  // ���������� � �������������
    struct Node* prev;      // ��������� �� ���������� ������� ������
    struct Node* next;      // ��������� �� ��������� ������� ������
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
Node* createNode(Department department) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->department = department;
    newNode->prev = NULL;
    newNode->next = NULL;
    return newNode;
}

// ������� �������� � ����� ������
void append(LinkedList* list, Department department) {
    Node* newNode = createNode(department);
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

// ������� �������� � ������ � ��������������� �� ������ �������������
void insert(LinkedList* list, Department department) {
    Node* newNode = createNode(department);
    if (list->head == NULL) {
        list->head = newNode;
        list->tail = newNode;
        return;
    }
    Node* current = list->head;
    while (current != NULL && current->department.departmentNumber < department.departmentNumber) {
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
void prepend(LinkedList* list, Department department) {
    Node* newNode = createNode(department);
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
        printf("����� �������������: %d\n", current->department.departmentNumber);
        printf("��������: %s\n", current->department.name);
        printf("�������������: %s\n", current->department.specialization);
        printf("���������� �����������: %d\n", current->department.employeeCount);
        printf("������������: %s\n", current->department.managerName);
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

    // ������� ���������� � ��������������
    Department department1 = { 123, "����� ������", "�������", 20, "������ ���� ��������" };
    Department department2 = { 456, "����� ����������", "IT", 30, "������ ���� ��������" };
    Department department3 = { 789, "����� ������", "HR", 15, "������� ����� ���������" };
    Department department4 = { 246, "���������� �����", "�������", 25, "������� ����� ��������" };
    Department department5 = { 357, "����� ����������", "���������", 18, "�������� ���� ���������" };

    // ������� ������������� ������� ������� � ������
    insert(&list, department1);
    insert(&list, department3);
    insert(&list, department2);
    insert(&list, department5);
    insert(&list, department4);

    // ������� ������������� ������� ������� � ������
    append(&list, department1);
    prepend(&list, department2);
    append(&list, department3);
    prepend(&list, department4);
    append(&list, department5);

    // �������� �������� �� ������ (��������, ������� ��������)
    removeNode(&list, list.head);

    // ����� ����������� ������
    displayList(&list);

    // ������������ ������
    freeList(&list);

    return 0;
}
