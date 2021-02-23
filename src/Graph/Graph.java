package Graph;

import java.util.*;

/**
 * Вариант 10 - Ориентированный граф
 *
 * Хранит вершины и дуги графа. Каждая дуга имеет определённое направление и вес (целое положительное число).
 * Каждая вершина имеет определённое имя.
 *
 * Методы: добавление новой вершины, добавление новой дуги, удаление вершины / дуги, изменение имени вершины,
 * изменение веса дуги, получение списка исходящих из вершины дуг, получение списка входящих в вершину дуг.
 */
public final class Graph {
    private static class Vertex {
        String name;
        Map<String, Integer> neighbours;

        Vertex(String name, HashMap<String, Integer> neighbors) {
            this.name = name;
            this.neighbours = neighbors;
        }
    }

    private final Map<String, Vertex> vertices = new HashMap<>();

    private Vertex getVertex(String name) { return vertices.get(name); }

    public void addVertex(String name) {
        if (vertices.containsKey(name)) throw new IllegalArgumentException("Attempts to add existent vertex");
        else vertices.put(name, new Vertex(name, new HashMap<>()));
    }

    public void connect(String first, String second, int value) {
        if (getVertex(first) == null || getVertex(second) == null)
            throw new IllegalArgumentException("Nonexistent vertex");
        else if (getVertex(first).neighbours.containsKey(second))
            throw new IllegalArgumentException("Attempts to add existent connection");
        else getVertex(first).neighbours.put(getVertex(second).name, value);
    }

    public void removeVertex(String name) {
        if (getVertex(name) == null) throw new IllegalArgumentException("Edition of nonexistent vertex");
        else {
            vertices.remove(name);
            vertices.forEach((element, vertex) -> vertex.neighbours.remove(name));
        }
    }

    public void removeConnection(String first, String second) {
        if (getVertex(first) == null || getVertex(second) == null || !getVertex(first).neighbours.containsKey(second))
            throw new IllegalArgumentException("Edition of nonexistent connection");
        else getVertex(first).neighbours.remove(second);
    }

    public void changeName(String lastName, String newName) {
        if (getVertex(lastName) == null) throw new IllegalArgumentException("Edition of nonexistent vertex");
        else {
            Vertex current = getVertex(lastName);
            vertices.remove(lastName);
            vertices.put(newName, current);
            vertices.forEach((element, vertex) -> {
                if (vertex.neighbours.containsKey(lastName)) {
                    int currentValue = vertex.neighbours.get(lastName);
                    vertex.neighbours.remove(lastName);
                    vertex.neighbours.put(newName, currentValue);
                }
            });
        }
    }

    public void changeValue(String first, String second, int newValue) {
        if (getVertex(first) == null || getVertex(second) == null || !getVertex(first).neighbours.containsKey(second))
            throw new IllegalArgumentException("Edition of nonexistent connection");
        else getVertex(first).neighbours.put(second, newValue);
    }

    public Map<String, Integer> getArcsOut(String name) {
        if (getVertex(name) == null) throw new IllegalArgumentException("Nonexistent vertex");

        Map<String, Integer> currentList = new HashMap<>(getVertex(name).neighbours);
        Map<String, Integer> result = new HashMap<>();
        currentList.forEach(result::put);
        return result;
    }

    public Map<String, Integer> getArcsIn(String name) {
        if (getVertex(name) == null) throw new IllegalArgumentException("Nonexistent vertex");

        Map<String, Integer> result = new HashMap<>();
        vertices.forEach((element, vertex) -> {
            if (vertex.neighbours.containsKey(name)) result.put(vertex.name, vertex.neighbours.get(name));
        });
        return result;
    }
}
