package Graph;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

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

        ArrayList<String> test1 = new ArrayList<>();
        test1.add("A -> B");
        test1.add("A -> D");
        assertEquals(test1, graph.getArcsOut("A"));

        assertThrows(NullPointerException.class, () -> graph.getArcsOut("C"));
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
        test1.add("A -> C");
        test1.add("A -> D");
        assertEquals(test1, graph.getArcsOut("A"));

        ArrayList<String> test2 = new ArrayList<>();
        test2.add("B -> C");
        test2.add("B -> D");
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

        ArrayList<String> test1 = new ArrayList<>();
        test1.add("A -> C");
        test1.add("A -> D");
        test1.add("A -> B renamed");
        assertEquals(test1, graph.getArcsOut("A"));

        ArrayList<String> test2 = new ArrayList<>();
        test2.add("B renamed -> C");
        test2.add("B renamed -> D");
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
        test1.put("A -> B", 10);
        test1.put("A -> C", 17);
        test1.put("A -> D", 2);
        assertEquals(test1, graph.getArcsWithValue("A"));
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

        ArrayList<String> test1 = new ArrayList<>();
        test1.add("A -> B");
        test1.add("A -> C");
        test1.add("A -> D");
        assertEquals(test1, graph.getArcsOut("A"));

        ArrayList<String> test2 = new ArrayList<>();
        test2.add("B -> C");
        test2.add("B -> D");
        assertEquals(test2, graph.getArcsOut("B"));

        ArrayList<String> test3 = new ArrayList<>();
        assertEquals(test3, graph.getArcsOut("D"));

        assertThrows(NullPointerException.class, () -> graph.getArcsOut("F"));
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

        ArrayList<String> test3 = new ArrayList<>();
        test3.add("A -> D");
        test3.add("B -> D");
        test3.add("C -> D");
        assertEquals(test3, graph.getArcsIn("D"));

        ArrayList<String> test2 = new ArrayList<>();
        test2.add("A -> B");
        assertEquals(test2, graph.getArcsIn("B"));

        ArrayList<String> test1 = new ArrayList<>();
        assertEquals(test1, graph.getArcsIn("A"));

        assertThrows(NullPointerException.class, () -> graph.getArcsIn("F"));
    }

    @Test
    public void Exceptions() {
        Graph graph1 = new Graph();
        graph1.addVertex("A");
        graph1.addVertex("B");
        graph1.addVertex("C");
        graph1.connect("A", "D", 10);
        assertThrows(NullPointerException.class, () -> graph1.getArcsOut("A"));

        Graph graph2 = new Graph();
        graph2.addVertex("A");
        graph2.addVertex("B");
        graph2.addVertex("A");
        graph2.connect("A", "B", 10);
        assertThrows(IllegalArgumentException.class, () -> graph2.getArcsOut("A"));

        Graph graph3 = new Graph();
        graph3.addVertex("A");
        graph3.addVertex("B");
        graph3.addVertex("C");
        graph3.connect("A", "B", 10);
        graph3.connect("A", "B", 10);
        assertThrows(IllegalArgumentException.class, () -> graph3.getArcsOut("A"));

        Graph graph4 = new Graph();
        graph4.addVertex("A");
        graph4.addVertex("B");
        graph4.addVertex("C");
        graph4.connect("A", "B", 10);
        graph4.removeVertex("F");
        assertThrows(NullPointerException.class, () -> graph4.getArcsOut("A"));

        Graph graph5 = new Graph();
        graph5.addVertex("A");
        graph5.addVertex("B");
        graph5.addVertex("C");
        graph5.connect("A", "B", 10);
        graph5.removeConnection("F", "A");
        assertThrows(IllegalArgumentException.class, () -> graph5.getArcsOut("A"));
    }
}