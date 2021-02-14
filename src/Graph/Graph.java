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
        TreeMap<String, Integer> neighbours;

        Vertex(String name, TreeMap<String, Integer> neighbors) {
            this.name = name;
            this.neighbours = neighbors;
        }
    }

    private final TreeMap<String, Vertex> vertices = new TreeMap<>();

    private Vertex getVertex(String name) { return vertices.get(name); }

    public void addVertex(String name) { vertices.put(name, new Vertex(name, new TreeMap<>())); }

    public void connect(String first, String second, int value) {
        getVertex(first).neighbours.put(getVertex(second).name, value);
    }

    public void removeVertex(String name) {
        vertices.remove(name);

        vertices.forEach((element, vertex) -> vertex.neighbours.remove(name));
    }

    public void removeConnection(String first, String second) {
        getVertex(first).neighbours.remove(second);
    }

    public void changeName(String lastName, String newName) {
        Vertex current = vertices.get(lastName);
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

    public void changeValue(String first, String second, int newValue) {
        getVertex(first).neighbours.put(second, newValue);
    }

    public TreeMap<String, Integer> getNeighboursWithValue(String name) {
        return getVertex(name).neighbours;
    }

    public List<String> getNeighboursOut(String name) {
        try {
            getVertex(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> listOfNeighbours = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : getVertex(name).neighbours.entrySet()) {
            listOfNeighbours.add(entry.getKey());
        }
        return listOfNeighbours;
    }

    public List<String> getNeighboursIn(String name) {
        try {
            getVertex(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> listOfNeighbours = new ArrayList<>();
        for (Map.Entry<String, Vertex> entry : vertices.entrySet()) {
            if (entry.getValue().neighbours.containsKey(name)) listOfNeighbours.add(entry.getValue().name);
        }
        return listOfNeighbours;
    }
}
