package org.example.structure.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Dijkstra {
    private int vertices; // Количество вершин
    private LinkedList<Edge>[] adjList; // Список смежности

    // Класс для представления ребра
    static class Edge {
        int vertex;
        int weight;

        Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public Dijkstra(int vertices) {
        this.vertices = vertices;
        adjList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    // Метод для добавления ребра в граф
    public void addEdge(int src, int dest, int weight) {
        adjList[src].add(new Edge(dest, weight));
        adjList[dest].add(new Edge(src, weight)); // Удалите эту строку для ориентированного графа
    }

    // Метод для выполнения алгоритма Дейкстры
    public void dijkstra(int src) {
        int[] dist = new int[vertices]; // Массив для хранения кратчайших расстояний от src до всех вершин
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(vertices, Comparator.comparingInt(e -> e.weight));
        pq.add(new Edge(src, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().vertex;

            for (Edge edge : adjList[u]) {
                int v = edge.vertex;
                int weight = edge.weight;

                // Если найден более короткий путь к v через u
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Edge(v, dist[v]));
                }
            }
        }

        // Вывод результатов
        printSolution(dist);
    }

    // Метод для вывода результатов
    void printSolution(int[] dist) {
        System.out.println("Vertex\t\tDistance from Source");
        for (int i = 0; i < vertices; i++) {
            System.out.println(i + "\t\t" + dist[i]);
        }
    }
}
