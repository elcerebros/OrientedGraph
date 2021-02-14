package Graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.TreeMap;

import static org.junit.Assert.*;

public class GraphTest {
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

        ArrayList<String> test1 = new ArrayList<>();
        test1.add("B");
        test1.add("D");
        assertEquals(test1, graph.getNeighboursOut("A"));

        assertThrows(Exception.class, () -> graph.getNeighboursOut("C"));
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

        ArrayList<String> test1 = new ArrayList<>();
        test1.add("C");
        test1.add("D");
        assertEquals(test1, graph.getNeighboursOut("A"));

        ArrayList<String> test2 = new ArrayList<>();
        test2.add("C");
        test2.add("D");
        assertEquals(test2, graph.getNeighboursOut("B"));
    }

    @Test
    public void changeName() {
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
        graph.changeName("B", "B renamed");

        ArrayList<String> test1 = new ArrayList<>();
        test1.add("B renamed");
        test1.add("C");
        test1.add("D");
        assertEquals(test1, graph.getNeighboursOut("A"));

        ArrayList<String> test2 = new ArrayList<>();
        test2.add("C");
        test2.add("D");
        assertEquals(test2, graph.getNeighboursOut("B renamed"));
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
        assertEquals(test1, graph.getNeighboursWithValue("A"));
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
        assertEquals(test1, graph.getNeighboursOut("A"));

        ArrayList<String> test2 = new ArrayList<>();
        test2.add("C");
        test2.add("D");
        assertEquals(test2, graph.getNeighboursOut("B"));

        ArrayList<String> test3 = new ArrayList<>();
        assertEquals(test3, graph.getNeighboursOut("D"));

        assertThrows(Exception.class, () -> graph.getNeighboursOut("F"));
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
        assertEquals(test1, graph.getNeighboursIn("A"));

        ArrayList<String> test2 = new ArrayList<>();
        test2.add("A");
        assertEquals(test2, graph.getNeighboursIn("B"));

        ArrayList<String> test3 = new ArrayList<>();
        test3.add("A");
        test3.add("B");
        test3.add("C");
        assertEquals(test3, graph.getNeighboursIn("D"));

        assertThrows(Exception.class, () -> graph.getNeighboursOut("F"));
    }
}