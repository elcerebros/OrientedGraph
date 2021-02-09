package Graph;

import java.util.Map;


public final class Graph {
    private static class Vertex {
        String name;
        Map<String, Integer> neighbors;

        Vertex(String name, Map<String, Integer> neighbors) {
            this.name = name;
            this.neighbors = neighbors;
        }
    }

    public Map<String, Vertex> vertices;

    public void addVertex(String name) {
        vertices.put(name, new Vertex(name, null));
    }

    public void connect(Vertex first, Vertex second, int value) {
        first.neighbors.put(second.toString(), value);
    }

    public boolean removeVertex(Vertex name) {
        if (vertices.get(name.toString()) == null) return false;
        vertices.remove(name);
        name.neighbors = null;
        vertices.forEach((element, vertexElement) -> vertexElement.neighbors.remove(name));
        return true;
    }

    public void renameVertex(String lastName, String newName) {
        Vertex current = vertices.get(lastName);
        vertices.remove(lastName);
        vertices.put(newName, current);
        vertices.forEach((element, vertexElement) -> {
            if (vertexElement.neighbors.containsKey(lastName)) {
                Integer currentValue = vertexElement.neighbors.get(lastName);
                vertexElement.neighbors.remove(lastName);
                vertexElement.neighbors.put(newName, currentValue);
            }
        });
    }
}
