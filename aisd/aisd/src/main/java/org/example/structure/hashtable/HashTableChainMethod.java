package org.example.structure.hashtable;

import org.example.model.Person;

import java.util.ArrayList;
import java.util.List;

// Хэш таблица методом цепочек
public class HashTableChainMethod {
    private final int size; // Размер хэш-таблицы
    private final List<Person>[] data; // Массив списков, используемый для хранения элементов

    public HashTableChainMethod(int size) {
        this.size = size;
        this.data = new List[size]; // Инициализация массива списков
        for (int i = 0; i < size; i++) {
            this.data[i] = new ArrayList<>(); // Инициализация каждого списка в массиве
        }
    }

    public void print() {
        int i = 1;
        System.out.println("======================");
        for (List<Person> element : data) {
            if (element.isEmpty()) {
                System.out.println("Элемент " + i + " - null"); // Вывод информации о пустых элементах
            } else {
                System.out.print("Элемент " + i + " - ");
                for (Person item : element) {
                    System.out.print("| " + item.getName() + " " + item.getAge() + " |"); // Вывод информации о каждом элементе в списке
                }
                System.out.println();
            }
            i++;
        }
        System.out.println("======================");
    }

    public void insert(Person personModel) {
        int index = getIndex(personModel); // Получение индекса для вставки элемента

        if (data[index] == null) {
            data[index] = new ArrayList<>(); // Создание нового списка, если в указанной ячейке еще нет списка
            data[index].add(personModel); // Добавление элемента в список
            return;
        }

        data[index].add(personModel); // Добавление элемента в существующий список
    }

    public void delete(Person personModel) {
        int index = getIndex(personModel); // Получение индекса элемента для удаления

        if (data[index].size() == 1) {
            data[index] = null; // Удаление списка, если он содержит только один элемент
            return;
        }

        for (Person item : data[index]) {
            if (item.getAge() == personModel.getAge() && item.getName().equals(personModel.getName())) {
                data[index].remove(item); // Удаление элемента из списка по критериям
                return;
            }
        }
    }

    public boolean find(Person personModel) {
        int index = getIndex(personModel); // Получение индекса элемента для поиска

        if (data[index] == null) {
            return false; // Возвращение false, если список пуст
        } else {
            for (Person item : data[index]) {
                if (item.getAge() == personModel.getAge() && item.getName().equals(personModel.getName())) {
                    return true; // Возвращение true, если элемент найден в списке
                }
            }
            return false; // Возвращение false, если элемент не найден в списке
        }
    }

    private int getIndex(Person personModel) {
        // Вычисление хэша для определения индекса элемента в массиве
        return (personModel.getAge() * personModel.getAge() + personModel.getName().length() * personModel.getName().length() + personModel.getAge() * personModel.getName().length()) % size;
    }
}