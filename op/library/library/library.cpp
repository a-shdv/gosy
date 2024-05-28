#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <locale.h>

// Структура для информации о читателе
typedef struct {
    int readerID;       // Номер читательского билета
    char surname[50];   // Фамилия
    char name[50];      // Имя
    char patronymic[50];// Отчество
    char street[100];    // Адрес проживания (только улица)
} ReaderInfo;

// Структура для элемента списка
typedef struct Node {
    ReaderInfo reader;  // Информация о читателе
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
Node* createNode(ReaderInfo reader) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    newNode->reader = reader;
    newNode->prev = NULL;
    newNode->next = NULL;
    return newNode;
}

// Вставка элемента в начало списка
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

// Вставка элемента в список с упорядочиванием по номеру читательского билета
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

// Вставка элемента в конец списка
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

// Удаление элемента из списка по номеру читательского билета
void deleteNode(LinkedList* list, int readerID) {
    Node* current = list->head;
    while (current != NULL && current->reader.readerID != readerID) {
        current = current->next;
    }
    if (current == NULL) {
        printf("Элемент с номером читательского билета %d не найден\n", readerID);
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

// Вывод списка на экран
void displayList(LinkedList* list) {
    if (list->head == NULL) {
        printf("Список пуст\n");
        return;
    }
    printf("Содержимое списка:\n");
    Node* current = list->head;
    while (current != NULL) {
        printf("Номер читательского билета: %d\n", current->reader.readerID);
        printf("Фамилия: %s\n", current->reader.surname);
        printf("Имя: %s\n", current->reader.name);
        printf("Отчество: %s\n", current->reader.patronymic);
        printf("Адрес: %s\n", current->reader.street);
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

    // Создание информации о читателях
    ReaderInfo reader1 = { 12345, "Иванов", "Иван", "Иванович", "ул. Ленина" };
    ReaderInfo reader2 = { 54321, "Петров", "Петр", "Петрович", "ул. Пушкина" };
    ReaderInfo reader3 = { 98765, "Сидоров", "Сидор", "Сидорович", "ул. Гагарина" };

    // Примеры использования функции вставки в список
    insert(&list, reader1);
    insert(&list, reader3);
    insert(&list, reader2);

/*
    // Добавление элемента в начало списка
    ReaderInfo newReader = { 11111, "Новый", "Читатель", "Нович", {"ул. Новая"} };
    prepend(&list, newReader);

    // Добавление элемента в конец списка
    ReaderInfo newReader2 = { 99999, "Последний", "Читатель", "Последний", {"ул. Последняя"} };
    append(&list, newReader2);

    // Вывод содержимого списка
    displayList(&list);

    // Удаление элемента из списка
    deleteNode(&list, 54321);
*/

    // Вывод содержимого списка после удаления
    displayList(&list);

    // Освобождение памяти
    freeList(&list);

    return 0;
}