#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <locale.h>

// Структура для информации о туре
typedef struct {
    int tourID;             // Идентификатор тура
    char name[100];          // Название тура
    char country[50];       // Страна, в которой проводится тур
    int seatsAvailable;     // Количество доступных мест в туре
    char responsible[50];   // ФИО ответственного за тур
} Tour;

// Структура для элемента списка
typedef struct Node {
    Tour tour;          // Информация о туре
    struct Node* prev;  // Указатель на предыдущий элемент списка
    struct Node* next;  // Указатель на следующий элемент списка
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
Node* createNode(Tour tour) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->tour = tour;
    newNode->prev = NULL;
    newNode->next = NULL;
    return newNode;
}

// Вставка элемента в конец списка
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

// Вставка элемента в начало списка
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

// Вставка элемента в список с упорядочиванием по идентификатору тура
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
        printf("Идентификатор тура: %d\n", current->tour.tourID);
        printf("Название тура: %s\n", current->tour.name);
        printf("Страна: %s\n", current->tour.country);
        printf("Доступные места: %d\n", current->tour.seatsAvailable);
        printf("Ответственный: %s\n", current->tour.responsible);
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

    // Примеры информации о турах
    Tour tour1 = { 101, "Экскурсия в Париж", "Франция", 25, "Иванов Иван Иванович" };
    Tour tour2 = { 202, "Отдых на Бали", "Индонезия", 15, "Петров Петр Петрович" };
    Tour tour3 = { 303, "Горнолыжный курорт в Швейцарии", "Швейцария", 20, "Сидоров Сидор Сидорович" };
    Tour tour4 = { 404, "Поход по Карпатам", "Украина", 10, "Козлова Елена Павловна" };
    Tour tour5 = { 505, "Круиз по Средиземному морю", "Греция", 30, "Никитина Анна Сергеевна" };

    // Примеры использования функции вставки в список
    insert(&list, tour1);
    insert(&list, tour3);
    insert(&list, tour2);
    insert(&list, tour5);
    insert(&list, tour4);

    // Примеры использования функции вставки в список
    append(&list, tour1);
    prepend(&list, tour2);
    append(&list, tour3);
    prepend(&list, tour4);
    append(&list, tour5);


    // Удаление элемента из списка (например, первого элемента)
    removeNode(&list, list.head);

    // Вывод содержимого списка
    displayList(&list);

    // Освобождение памяти
    freeList(&list);

    return 0;
}