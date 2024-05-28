package org.example.structure.hashtable;

import org.example.model.Person;

// Хэш таблица с двойным хэшированием
public class HashTableDoubleHash {
    private final int size; // Размер хэш-таблицы
    private final Person[] data; // Массив элементов хэш-таблицы

    public HashTableDoubleHash(int size) {
        this.size = size;
        this.data = new Person[size]; // Инициализация массива элементов
    }

    public void print() {
        int i = 1;
        System.out.println("======================");
        for (Person element : data) {
            if (element == null || element.isDeleted()) {
                System.out.println("Элемент " + i + " - null"); // Вывод информации о пустых или удаленных элементах
            } else {
                System.out.println("Элемент " + i + " - " + element.getName() + " " + element.getAge()); // Вывод информации о каждом элементе
            }
            i++;
        }
        System.out.println("======================");
    }

    public void insert(Person personModel) {
        int index = getIndex(personModel); // Получение индекса для вставки элемента

        if (data[index] == null || data[index].isDeleted()) {
            data[index] = personModel; // Вставка элемента, если ячейка пуста или содержит удаленный элемент
            return;
        }

        for (int i = 0; i < size; i++) {
            index = helperHash(personModel, i); // Вычисление нового индекса с помощью двойного хэширования
            if (data[index] == null || data[index].isDeleted()) {
                data[index] = personModel; // Вставка элемента, если ячейка пуста или содержит удаленный элемент
                return;
            }
        }

        System.out.println("Таблица переполнена"); // Вывод сообщения об ошибке, если таблица переполнена
    }

    public void delete(Person personModel) {
        for (int i = 0; i < size; i++) {
            int index = helperHash(personModel, i); // Получение индекса элемента для удаления
            if (data[index] != null && data[index].getAge() == personModel.getAge()
                    && data[index].getName().equals(personModel.getName())
                    && data[index].isDeleted() == personModel.isDeleted()) {
                data[index].setDeleted(true); // Установка флага удаления для найденного элемента
            }
        }
    }

    public boolean find(Person personModel) {
        for (int i = 0; i < size; i++) {
            int index = helperHash(personModel, i); // Получение индекса элемента для поиска
            if (data[index] == null) {
                return false; // Возвращение false, если ячейка пуста
            }
            if (data[index].getAge() == personModel.getAge()
                    && data[index].getName().equals(personModel.getName())
                    && data[index].isDeleted() == personModel.isDeleted()) {
                return true; // Возвращение true, если элемент найден
            }
        }
        return false; // Возвращение false, если элемент не найден
    }

    private int getIndex(Person personModel) {
        // Вычисление хэша для определения индекса элемента в массиве
        return (personModel.getAge() * personModel.getAge() +
                personModel.getName().length() * personModel.getName().length() +
                personModel.getAge() * personModel.getName().length()) % size;
    }

    private int helperHash(Person personModel, int step) {
        // Вычисление второго хэша с помощью двойного хэширования
        return (getIndex(personModel) + step * personModel.getAge() * personModel.getName().length()) % size;
    }
}
