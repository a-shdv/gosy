#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <locale.h>

// ��������� ��� ���������� � ��������
typedef struct {
    int readerID;       // ����� ������������� ������
    char surname[50];   // �������
    char name[50];      // ���
    char patronymic[50];// ��������
    char street[100];    // ����� ���������� (������ �����)
} ReaderInfo;

// ��������� ��� �������� ������
typedef struct Node {
    ReaderInfo reader;  // ���������� � ��������
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
Node* createNode(ReaderInfo reader) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->reader = reader;
    newNode->prev = NULL;
    newNode->next = NULL;
    return newNode;
}

// ������� �������� � ������ ������
void prepend(LinkedList* list, ReaderInfo reader) {
    Node* newNode = createNode(reader);
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

// ������� �������� � ������ � ��������������� �� ������ ������������� ������
void insert(LinkedList* list, ReaderInfo reader) {
    Node* newNode = createNode(reader);
    if (list->head == NULL) {
        list->head = newNode;
        list->tail = newNode;
        return;
    }
    Node* current = list->head;
    while (current != NULL && current->reader.readerID < reader.readerID) {
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

// ������� �������� � ����� ������
void append(LinkedList* list, ReaderInfo reader) {
    Node* newNode = createNode(reader);
    if (list->tail == NULL) {
        list->head = newNode;
        list->tail = newNode;
    }
    else {
        newNode->prev = list->tail;
        list->tail->next = newNode;
        list->tail = newNode;
    }
}

// �������� �������� �� ������ �� ������ ������������� ������
void deleteNode(LinkedList* list, int readerID) {
    Node* current = list->head;
    while (current != NULL && current->reader.readerID != readerID) {
        current = current->next;
    }
    if (current == NULL) {
        printf("������� � ������� ������������� ������ %d �� ������\n", readerID);
        return;
    }
    if (current == list->head) {
        list->head = current->next;
        if (list->head != NULL) {
            list->head->prev = NULL;
        }
        else {
            list->tail = NULL;
        }
    }
    else if (current == list->tail) {
        list->tail = current->prev;
        list->tail->next = NULL;
    }
    else {
        current->prev->next = current->next;
        current->next->prev = current->prev;
    }
    free(current);
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
        printf("����� ������������� ������: %d\n", current->reader.readerID);
        printf("�������: %s\n", current->reader.surname);
        printf("���: %s\n", current->reader.name);
        printf("��������: %s\n", current->reader.patronymic);
        printf("�����: %s\n", current->reader.street);
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

    // �������� ���������� � ���������
    ReaderInfo reader1 = { 12345, "������", "����", "��������", "��. ������" };
    ReaderInfo reader2 = { 54321, "������", "����", "��������", "��. �������" };
    ReaderInfo reader3 = { 98765, "�������", "�����", "���������", "��. ��������" };

    // ������� ������������� ������� ������� � ������
    insert(&list, reader1);
    insert(&list, reader3);
    insert(&list, reader2);

/*
    // ���������� �������� � ������ ������
    ReaderInfo newReader = { 11111, "�����", "��������", "�����", {"��. �����"} };
    prepend(&list, newReader);

    // ���������� �������� � ����� ������
    ReaderInfo newReader2 = { 99999, "���������", "��������", "���������", {"��. ���������"} };
    append(&list, newReader2);

    // ����� ����������� ������
    displayList(&list);

    // �������� �������� �� ������
    deleteNode(&list, 54321);
*/

    // ����� ����������� ������ ����� ��������
    displayList(&list);

    // ������������ ������
    freeList(&list);

    return 0;
}