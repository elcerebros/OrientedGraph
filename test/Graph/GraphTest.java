package Graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

public class GraphTest {
    @Test
    public void getNeighbors() {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.connect("A", "B", 10);
        graph.connect("A", "C", 1);
        graph.connect("A", "D", 2);
        graph.connect("B", "C", 7);
        graph.connect("B", "D", 8);
        graph.connect("C", "D", 6);

        TreeMap<String, Integer> test1 = new TreeMap<>();
        test1.put("B", 10);
        test1.put("C", 1);
        test1.put("D", 2);
        assertEquals(test1, graph.getNeighbours("A"));

        TreeMap<String, Integer> test2 = new TreeMap<>();
        test2.put("C", 7);
        test2.put("D", 8);
        assertEquals(test2, graph.getNeighbours("B"));

        TreeMap<String, Integer> test3 = new TreeMap<>();
        assertEquals(test3, graph.getNeighbours("D"));
    }

    @Test
    public void renameVertex() {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.connect("A", "B", 10);
        graph.connect("A", "C", 1);
        graph.connect("A", "D", 2);
        graph.connect("B", "C", 7);
        graph.connect("B", "D", 8);
        graph.connect("C", "D", 6);
        graph.renameVertex("B", "B renamed");

        TreeMap<String, Integer> test1 = new TreeMap<>();
        test1.put("B renamed", 10);
        test1.put("C", 1);
        test1.put("D", 2);
        assertEquals(test1, graph.getNeighbours("A"));

        TreeMap<String, Integer> test2 = new TreeMap<>();
        test2.put("C", 7);
        test2.put("D", 8);
        assertEquals(test2, graph.getNeighbours("B renamed"));
    }

    @Test
    public void removeVertex() {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.connect("A", "B", 10);
        graph.connect("A", "C", 1);
        graph.connect("A", "D", 2);
        graph.connect("B", "C", 7);
        graph.connect("B", "D", 8);
        graph.connect("C", "D", 6);
        graph.removeVertex("C");

        TreeMap<String, Integer> test1 = new TreeMap<>();
        test1.put("B", 10);
        test1.put("D", 2);
        assertEquals(test1, graph.getNeighbours("A"));

        TreeMap<String, Integer> test2 = new TreeMap<>();
        test2.put("D", 8);
        assertEquals(test2, graph.getNeighbours("B"));
    }

    @Test
    public void removeConnection() {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.connect("A", "B", 10);
        graph.connect("A", "C", 1);
        graph.connect("A", "D", 2);
        graph.connect("B", "C", 7);
        graph.connect("B", "D", 8);
        graph.connect("C", "D", 6);
        graph.removeConnection("A", "B");

        TreeMap<String, Integer> test1 = new TreeMap<>();
        test1.put("C", 1);
        test1.put("D", 2);
        assertEquals(test1, graph.getNeighbours("A"));

        TreeMap<String, Integer> test2 = new TreeMap<>();
        test2.put("C", 7);
        test2.put("D", 8);
        assertEquals(test2, graph.getNeighbours("B"));
    }

    @Test
    public void changeValue() {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.connect("A", "B", 10);
        graph.connect("A", "C", 1);
        graph.connect("A", "D", 2);
        graph.connect("B", "C", 7);
        graph.connect("B", "D", 8);
        graph.connect("C", "D", 6);
        graph.changeValue("A", "C", 17);

        TreeMap<String, Integer> test1 = new TreeMap<>();
        test1.put("B", 10);
        test1.put("C", 17);
        test1.put("D", 2);
        assertEquals(test1, graph.getNeighbours("A"));
    }

    @Test
    public void getNeighborsOut() {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.connect("A", "B", 10);
        graph.connect("A", "C", 1);
        graph.connect("A", "D", 2);
        graph.connect("B", "C", 7);
        graph.connect("B", "D", 8);
        graph.connect("C", "D", 6);

        ArrayList<String> test1 = new ArrayList<>();
        test1.add("B");
        test1.add("C");
        test1.add("D");
        assertEquals(test1, graph.getNeighborsOut("A"));

        ArrayList<String> test2 = new ArrayList<>();
        test2.add("C");
        test2.add("D");
        assertEquals(test2, graph.getNeighborsOut("B"));

        ArrayList<String> test3 = new ArrayList<>();
        assertEquals(test3, graph.getNeighborsOut("D"));
    }

    @Test
    public void getNeighborsIn() {
        Graph graph = new Graph();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.connect("A", "B", 10);
        graph.connect("A", "C", 1);
        graph.connect("A", "D", 2);
        graph.connect("B", "C", 7);
        graph.connect("B", "D", 8);
        graph.connect("C", "D", 6);

        ArrayList<String> test1 = new ArrayList<>();
        assertEquals(test1, graph.getNeighborsIn("A"));

        ArrayList<String> test2 = new ArrayList<>();
        test2.add("A");
        assertEquals(test2, graph.getNeighborsIn("B"));

        ArrayList<String> test3 = new ArrayList<>();
        test3.add("A");
        test3.add("B");
        test3.add("C");
        assertEquals(test3, graph.getNeighborsIn("D"));
    }
}