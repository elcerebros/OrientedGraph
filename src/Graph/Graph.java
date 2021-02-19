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

    private final Map<String, Integer> errors = new HashMap<>() {{
        put("Nonexistent vertex", 0);
        put("Attempts to add existent vertex", 0);
        put("Attempts to add existent connection", 0);
        put("Edition of nonexistent vertex", 0);
        put("Edition of nonexistent connection", 0);
    }};

    private void findErrors() throws IllegalArgumentException {
        if (errors.get("Nonexistent vertex") > 0)
            throw new NullPointerException("Nonexistent vertex");
        if (errors.get("Attempts to add existent vertex") > 0)
            throw new IllegalArgumentException("Attempts to add existent vertex");
        if (errors.get("Attempts to add existent connection") > 0)
            throw new IllegalArgumentException("Attempts to add existent connection");
        if (errors.get("Edition of nonexistent vertex") > 0)
            throw new NullPointerException("Edition of nonexistent vertex");
        if (errors.get("Edition of nonexistent connection") > 0)
            throw new IllegalArgumentException("Nonexistent connections");
    }

    private Vertex getVertex(String name) { return vertices.get(name); }

    public void addVertex(String name) {
        if (vertices.containsKey(name)) errors.replace("Attempts to add existent vertex", 1);

        else vertices.put(name, new Vertex(name, new HashMap<>()));
    }

    public void connect(String first, String second, int value) {
        if (getVertex(first) == null || getVertex(second) == null) errors.replace("Nonexistent vertex", 1);
        else if (getVertex(first).neighbours.containsKey(second))
            errors.replace("Attempts to add existent connection", 1);

        else getVertex(first).neighbours.put(getVertex(second).name, value);
    }

    public void removeVertex(String name) {
        if (getVertex(name) == null) errors.replace("Edition of nonexistent vertex", 1);

        else {
            vertices.remove(name);
            vertices.forEach((element, vertex) -> vertex.neighbours.remove(name));
        }
    }

    public void removeConnection(String first, String second) {
        if (getVertex(first) == null || getVertex(second) == null || !getVertex(first).neighbours.containsKey(second))
            errors.replace("Edition of nonexistent connection", 1);

        else getVertex(first).neighbours.remove(second);
    }

    public void changeName(String lastName, String newName) {
        if (getVertex(lastName) == null) errors.replace("Edition of nonexistent vertex", 1);

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
            errors.replace("Edition of nonexistent connection", 1);

        else getVertex(first).neighbours.put(second, newValue);
    }

    public List<String> getArcsOut(String name) {
        if (getVertex(name) == null) errors.replace("Nonexistent vertex", 1);
        findErrors();

        List<String> currentList = new ArrayList<>(getVertex(name).neighbours.keySet());
        List<String> result = new ArrayList<>();
        currentList.forEach(element -> result.add(name + " -> " + element));
        return result;
    }

    public List<String> getArcsIn(String name) {
        if (getVertex(name) == null) errors.replace("Nonexistent vertex", 1);
        findErrors();

        List<String> result = new ArrayList<>();
        vertices.forEach((element, vertex) -> {
            if (vertex.neighbours.containsKey(name)) result.add(vertex.name + " -> " + name);
        });
        return result;
    }

    public Map<String, Integer> getArcsWithValue(String name) {
        if (getVertex(name) == null) errors.replace("Nonexistent vertex", 1);
        findErrors();

        Map<String, Integer> result = new HashMap<>();
        getVertex(name).neighbours.forEach((vertex, value) -> result.put(name + " -> " + vertex, value));
        return result;
    }
}
