package org.example.structure.graph;

import java.util.*;

// Граф, алгоритм обхода в ширину, алгоритм обхода в длину
public class Graph {
    private static class Vertex {
        private int id;
        private List<Edge> edges = new ArrayList<>();

        public Vertex(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public List<Edge> getEdges() {
            return edges;
        }

        public void addEdge(Edge edge) {
            edges.add(edge);
        }

        @Override
        public String toString() {
            return "Element " + id;
        }
    }

    private static class Edge {
        private Vertex vertex;

        public Edge(Vertex vertex) {
            this.vertex = vertex;
        }

        public Vertex getVertex() {
            return vertex;
        }
    }


    // List to store vertices
    private List<Vertex> vertices = new ArrayList<>();

    // Method to add a new vertex
    public void addVertex() {
        vertices.add(new Vertex(vertices.size()));
    }

    // Method to get a vertex by id
    public Vertex getVertex(int id) {
        for (Vertex vertex : vertices) {
            if (vertex.getId() == id) {
                return vertex;
            }
        }
        return null;
    }

    // Method to add an edge between two vertices
    public void addEdge(int id1, int id2) {
        Vertex vertex1 = getVertex(id1);
        Vertex vertex2 = getVertex(id2);
        if (vertex1 != null && vertex2 != null) {
            vertex1.addEdge(new Edge(vertex2));
            vertex2.addEdge(new Edge(vertex1));
        }
    }

    // Method to perform Breadth-First Search starting from a given vertex id
    public void bfs(int id) {
        int[] array = new int[vertices.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = -1;
        }
        Queue<Vertex> queue = new LinkedList<>();

        queue.add(getVertex(id));

        int iteration = 1;
        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();

            System.out.println("Iteration " + iteration + ": Algorithm entered vertex " + vertex.getId());
            array[vertex.getId()] = iteration;

            List<Edge> edges = vertex.getEdges();
            for (Edge edge : edges) {
                if (array[edge.getVertex().getId()] == -1 && !queue.contains(getVertex(edge.getVertex().getId()))) {
                    System.out.println("Algorithm added vertex " + edge.getVertex().getId() + " to the queue");
                    queue.add(edge.getVertex());
                }
            }
            iteration++;
        }

        System.out.println("Algorithm traversed all vertices");
    }

    // Method to perform Depth-First Search starting from a given vertex id
    public void dfs(int startId) {
        boolean[] visited = new boolean[vertices.size()];
        var vertex = getVertex(startId);
        Stack<Vertex> stack = new Stack<>();
        stack.push(vertex);

        while (!stack.isEmpty()) {
            Vertex currentVertex = stack.pop();

            if (!visited[currentVertex.getId()]) {
                visited[currentVertex.getId()] = true;
                System.out.println("Visited vertex: " + currentVertex.getId());

                // Process adjacent vertices
                for (Edge edge : currentVertex.getEdges()) {
                    Vertex adjacentVertex = edge.getVertex();
                    if (!visited[adjacentVertex.getId()]) {
                        stack.push(adjacentVertex);
                    }
                }
            }
        }
    }
}