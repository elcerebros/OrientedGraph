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

    public void addVertex(String name) {
        vertices.put(name, new Vertex(name, new TreeMap<>()));
    }

    public void connect(String first, String second, int value) {
        vertices.get(first).neighbours.put(vertices.get(second).name, value);
    }

    public void removeVertex(String name) {
        try {
            vertices.get(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        vertices.remove(name);
        vertices.forEach((element, vertexElement) -> vertexElement.neighbours.remove(name));
    }

    public void removeConnection(String first, String second) {
        vertices.get(first).neighbours.remove(second);
    }

    public void renameVertex(String lastName, String newName) {
        Vertex current = vertices.get(lastName);
        vertices.remove(lastName);
        vertices.put(newName, current);
        vertices.forEach((element, vertexElement) -> {
            if (vertexElement.neighbours.containsKey(lastName)) {
                Integer currentValue = vertexElement.neighbours.get(lastName);
                vertexElement.neighbours.remove(lastName);
                vertexElement.neighbours.put(newName, currentValue);
            }
        });
    }

    public void changeValue(String first, String second, int newValue) {
        vertices.get(first).neighbours.put(second, newValue);
    }

    public List<String> getNeighborsOut(String name) {
        List<String> listOfNeighbours = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : vertices.get(name).neighbours.entrySet()) {
            listOfNeighbours.add(entry.getKey());
        }
        return listOfNeighbours;
    }

    public List<String> getNeighborsIn(String name) {
        List<String> listOfNeighbours = new ArrayList<>();
        for (Map.Entry<String, Vertex> entry : vertices.entrySet()) {
            if (entry.getValue().neighbours.containsKey(name)) listOfNeighbours.add(entry.getValue().name);
        }
        return listOfNeighbours;
    }

    public TreeMap<String, Integer> getNeighbours(String name) {
        return vertices.get(name).neighbours;
    }
}
