#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <locale.h>

// Структура для информации о подразделении
typedef struct {
    int departmentNumber;       // Номер подразделения
    char name[50];              // Название
    char specialization[50];    // Специализация
    int employeeCount;          // Количество сотрудников
    char managerName[50];       // ФИО руководителя
} Department;

// Структура для элемента списка
typedef struct Node {
    Department department;  // Информация о подразделении
    struct Node* prev;      // Указатель на предыдущий элемент списка
    struct Node* next;      // Указатель на следующий элемент списка
} Node;

// Структура для самого списка
typedef struct {
    Node* head; // Указатель на первый элемент списка
    Node* tail; // Указатель на последний элемент списка
} LinkedList;

// Инициализация списка
void initializeList(LinkedList* list) {
    list->head = NULL;
    list->tail = NULL;
}

// Создание нового элемента списка
Node* createNode(Department department) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->department = department;
    newNode->prev = NULL;
    newNode->next = NULL;
    return newNode;
}

// Вставка элемента в конец списка
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

// Вставка элемента в список с упорядочиванием по номеру подразделения
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

// Вставка элемента в начало списка
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

// Удаление элемента из списка
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


// Вывод списка на экран
void displayList(LinkedList* list) {
    if (list->head == NULL) {
        printf("Список пуст\n");
        return;
    }
    printf("Содержимое списка:\n");
    Node* current = list->head;
    while (current != NULL) {
        printf("Номер подразделения: %d\n", current->department.departmentNumber);
        printf("Название: %s\n", current->department.name);
        printf("Специализация: %s\n", current->department.specialization);
        printf("Количество сотрудников: %d\n", current->department.employeeCount);
        printf("Руководитель: %s\n", current->department.managerName);
        printf("\n");
        current = current->next;
    }
}

// Освобождение памяти, выделенной под список
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

    // Примеры информации о подразделениях
    Department department1 = { 123, "Отдел продаж", "Продажи", 20, "Иванов Иван Иванович" };
    Department department2 = { 456, "Отдел разработки", "IT", 30, "Петров Петр Петрович" };
    Department department3 = { 789, "Отдел кадров", "HR", 15, "Сидоров Сидор Сидорович" };
    Department department4 = { 246, "Финансовый отдел", "Финансы", 25, "Козлова Елена Павловна" };
    Department department5 = { 357, "Отдел маркетинга", "Маркетинг", 18, "Никитина Анна Сергеевна" };

    // Примеры использования функции вставки в список
    insert(&list, department1);
    insert(&list, department3);
    insert(&list, department2);
    insert(&list, department5);
    insert(&list, department4);

    // Примеры использования функции вставки в список
    append(&list, department1);
    prepend(&list, department2);
    append(&list, department3);
    prepend(&list, department4);
    append(&list, department5);

    // Удаление элемента из списка (например, первого элемента)
    removeNode(&list, list.head);

    // Вывод содержимого списка
    displayList(&list);

    // Освобождение памяти
    freeList(&list);

    return 0;
}
