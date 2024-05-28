#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <clocale>

// Структура для информации о транспорте
typedef struct {
    char licensePlate[20];  // Государственный номер
    char brand[50];         // Марка
    char model[50];         // Модель
    int capacity;           // Вместимость (количество пассажиров)
    int year;               // Год выпуска
} Autotransport;

// Структура для элемента списка
typedef struct Node {
    Autotransport transport;    // Информация о транспорте
    struct Node* prev;          // Указатель на предыдущий элемент списка
    struct Node* next;          // Указатель на следующий элемент списка
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
Node* createNode(Autotransport transport) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->transport = transport;
    newNode->prev = NULL;
    newNode->next = NULL;
    return newNode;
}

// Вставка элемента в список с упорядочиванием по государственному номеру
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
            printf("Элемент с таким государственным номером уже существует\n");
            free(newNode);
            return;
        }
        newNode->next = current;
        newNode->prev = current->prev;
        current->prev->next = newNode;
        current->prev = newNode;
    }
}

// Вставка элемента в конец списка
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

// Вставка элемента в начало списка
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
        printf("Государственный номер: %s\n", current->transport.licensePlate);
        printf("Марка: %s\n", current->transport.brand);
        printf("Модель: %s\n", current->transport.model);
        printf("Вместимость: %d\n", current->transport.capacity);
        printf("Год выпуска: %d\n", current->transport.year);
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

    // Примеры информации о транспорте
    Autotransport transport1 = { "A123BC", "Toyota", "Camry", 5, 2015 };
    Autotransport transport2 = { "B456DE", "Honda", "Civic", 4, 2018 };
    Autotransport transport3 = { "C789FG", "Ford", "Focus", 4, 2016 };
    Autotransport transport4 = { "B123BV", "Changan", "CS55 PLUS", 5, 2023 };
    Autotransport transport5 = { "G322SD", "Changan", "Uni-T", 4, 2023 };

    // Примеры использования функции вставки в список
    insert(&list, transport1);
    insert(&list, transport2);
    insert(&list, transport3);
    insert(&list, transport4);
    insert(&list, transport5);

    // Вывод содержимого списка
    displayList(&list);

    // Освобождение памяти
    freeList(&list);

    return 0;
}
