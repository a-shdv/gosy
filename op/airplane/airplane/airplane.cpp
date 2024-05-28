#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <locale.h>

// Структура для информации о самолете
typedef struct {
    char registrationNumber[20];   // Бортовой номер
    char type[50];                 // Тип самолета (пассажирский, транспортный и т.д.)
    char model[50];                // Модель
    int cargoCapacity;             // Грузоподъемность
    int range;                     // Дальность полета
} Airplane;

// Структура для элемента списка
typedef struct Node {
    Airplane airplane;    // Информация о самолете
    struct Node* prev;    // Указатель на предыдущий элемент списка
    struct Node* next;    // Указатель на следующий элемент списка
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
Node* createNode(Airplane airplane) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->airplane = airplane;
    newNode->prev = NULL;
    newNode->next = NULL;
    return newNode;
}

// Вставка элемента в конец списка
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

// Вставка элемента в список с упорядочиванием по бортовому номеру
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

// Вставка элемента в начало списка
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
        printf("Бортовой номер: %s\n", current->airplane.registrationNumber);
        printf("Тип: %s\n", current->airplane.type);
        printf("Модель: %s\n", current->airplane.model);
        printf("Грузоподъемность: %d\n", current
            ->airplane.cargoCapacity);
        printf("Дальность полета: %d\n", current->airplane.range);
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
    setlocale(LC_ALL, "Russian");

    LinkedList list;
    initializeList(&list);

    // Примеры информации о самолетах
    Airplane airplane1 = { "SU123", "Пассажирский", "Airbus A320", 50000, 5000 };
    Airplane airplane2 = { "BA456", "Грузовой", "Boeing 747", 200000, 10000 };
    Airplane airplane3 = { "AF789", "Пассажирский", "Airbus A380", 80000, 7000 };
    Airplane airplane4 = { "LH246", "Пассажирский", "Boeing 737", 60000, 6000 };
    Airplane airplane5 = { "EK357", "Грузовой", "Antonov An-124", 150000, 8000 };

    // Примеры использования функции вставки в список
    insert(&list, airplane1);
    insert(&list, airplane3);
    insert(&list, airplane2);
    insert(&list, airplane5);
    insert(&list, airplane4);

    // Примеры использования функции вставки в список
    append(&list, airplane1);
    prepend(&list, airplane2);
    append(&list, airplane3);
    prepend(&list, airplane4);
    append(&list, airplane5);

    // Удаление элемента из списка (например, первого элемента)
    removeNode(&list, list.head);

    // Вывод содержимого списка
    displayList(&list);

    // Освобождение памяти
    freeList(&list);

    return 0;
}
