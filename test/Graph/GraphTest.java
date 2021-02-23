package Graph;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

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

        Map<String, Integer> test1 = new HashMap<>();
        test1.put("B", 10);
        test1.put("D", 2);
        assertEquals(test1, graph.getArcsOut("A"));

        assertThrows(IllegalArgumentException.class, () -> graph.getArcsOut("C"));
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

        Map<String, Integer> test1 = new HashMap<>();
        test1.put("C", 1);
        test1.put("D", 2);
        assertEquals(test1, graph.getArcsOut("A"));

        Map<String, Integer> test2 = new HashMap<>();
        test2.put("C", 7);
        test2.put("D", 8);
        assertEquals(test2, graph.getArcsOut("B"));
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

        Map<String, Integer> test1 = new HashMap<>();
        test1.put("C", 1);
        test1.put("D", 2);
        test1.put("B renamed", 10);
        assertEquals(test1, graph.getArcsOut("A"));

        Map<String, Integer> test2 = new HashMap<>();
        test2.put("C", 7);
        test2.put("D", 8);
        assertEquals(test2, graph.getArcsOut("B renamed"));
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

        HashMap<String, Integer> test1 = new HashMap<>();
        test1.put("B", 10);
        test1.put("C", 17);
        test1.put("D", 2);
        assertEquals(test1, graph.getArcsOut("A"));
    }

    @Test
    public void getArcsOut() {
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

        Map<String, Integer> test1 = new HashMap<>();
        test1.put("B", 10);
        test1.put("C", 1);
        test1.put("D", 2);
        assertEquals(test1, graph.getArcsOut("A"));

        Map<String, Integer> test2 = new HashMap<>();
        test2.put("C", 7);
        test2.put("D", 8);
        assertEquals(test2, graph.getArcsOut("B"));

        Map<String, Integer> test3 = new HashMap<>();
        assertEquals(test3, graph.getArcsOut("D"));

        assertThrows(IllegalArgumentException.class, () -> graph.getArcsOut("F"));
    }

    @Test
    public void getArcsIn() {
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

        Map<String, Integer> test1 = new HashMap<>();
        test1.put("A", 2);
        test1.put("B", 8);
        test1.put("C", 6);
        assertEquals(test1, graph.getArcsIn("D"));

        Map<String, Integer> test2 = new HashMap<>();
        test2.put("A", 10);
        assertEquals(test2, graph.getArcsIn("B"));

        Map<String, Integer> test3 = new HashMap<>();
        assertEquals(test3, graph.getArcsIn("A"));

        assertThrows(IllegalArgumentException.class, () -> graph.getArcsIn("F"));
    }

    @Test
    public void Exceptions() {
        Graph graph1 = new Graph();
        graph1.addVertex("A");
        graph1.addVertex("B");
        graph1.addVertex("C");
        assertThrows(IllegalArgumentException.class, () -> graph1.connect("A", "D", 10));

        Graph graph2 = new Graph();
        graph2.addVertex("A");
        graph2.addVertex("B");
        assertThrows(IllegalArgumentException.class, () -> graph2.addVertex("A"));

        Graph graph3 = new Graph();
        graph3.addVertex("A");
        graph3.addVertex("B");
        graph3.addVertex("C");
        graph3.connect("A", "B", 10);
        assertThrows(IllegalArgumentException.class, () -> graph3.connect("A", "B", 10));

        Graph graph4 = new Graph();
        graph4.addVertex("A");
        graph4.addVertex("B");
        graph4.addVertex("C");
        graph4.connect("A", "B", 10);
        assertThrows(IllegalArgumentException.class, () -> graph4.removeVertex("F"));

        Graph graph5 = new Graph();
        graph5.addVertex("A");
        graph5.addVertex("B");
        graph5.addVertex("C");
        graph5.connect("A", "B", 10);
        assertThrows(IllegalArgumentException.class, () -> graph5.removeConnection("F", "A"));
    }
}