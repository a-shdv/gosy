package org.example.structure.hashtable;

import org.example.model.Person;

// Хэш таблица с линейным пробированием
public class HashTableLineProbing {
    private final int size; // Размер хэш-таблицы
    private final Person[] data; // Массив элементов хэш-таблицы
    private final int step; // Шаг для линейного пробирования

    public HashTableLineProbing(int size, int step) {
        this.size = size;
        this.data = new Person[size]; // Инициализация массива элементов
        this.step = step; // Инициализация шага
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

        for (int i = 1; index + i * step < size; i++) {
            if (data[index + i * step] == null || data[index + i * step].isDeleted()) {
                data[index + i * step] = personModel; // Вставка элемента на позицию, найденную с использованием линейного пробирования
                return;
            }
        }

        System.out.println("Таблица переполнена"); // Вывод сообщения об ошибке, если таблица переполнена
    }

    public void delete(Person person) {
        int index = getIndex(person); // Получение индекса элемента для удаления

        for (int i = 0; index + i * step < size; i++) {
            // Проверка, что элемент не равен null перед доступом к его свойствам
            if (data[index + i * step] != null &&
                    data[index + i * step].getAge() == person.getAge() &&
                    data[index + i * step].getName().equals(person.getName()) &&
                    data[index + i * step].isDeleted() == person.isDeleted()) {
                data[index + i * step].setDeleted(true); // Установка флага удаления для найденного элемента
            }
        }
    }

    public boolean find(Person personModel) {
        int index = getIndex(personModel); // Получение индекса элемента для поиска

        if (data[index] == null) {
            return false; // Возвращение false, если ячейка пуста
        } else {
            for (int i = 0; index + i * step < size; i++) {
                if (data[index + i * step] == null) {
                    return false; // Возвращение false, если элемент не найден
                }
                if (data[index + i * step].getAge() == personModel.getAge() &&
                        data[index + i * step].getName().equals(personModel.getName()) &&
                        data[index + i * step].isDeleted() == personModel.isDeleted()) {
                    return true; // Возвращение true, если элемент найден
                }
            }
            return false; // Возвращение false, если элемент не найден
        }
    }

    private int getIndex(Person personModel) {
        // Вычисление хэша для определения индекса элемента в массиве
        return (personModel.getAge() * personModel.getAge() +
                personModel.getName().length() * personModel.getName().length() +
                personModel.getAge() * personModel.getName().length()) % size;
    }
}
