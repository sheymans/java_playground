package net.stijn.java_playground;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

class GraphTest {

    @Test
    void graphConstruction() {
        Node n1 = new Node("a");
        Node n2 = new Node("b");
        n1.addChild(n2);

        Graph g = new Graph();
        g.addNode(n1);
        g.addNode(n2);
    }

    @Test
    void getNeighbors() {
        Node n1 = new Node("a");
        Node n2 = new Node("b");
        n1.addChild(n2);

        Graph g = new Graph();
        g.addNode(n1);
        g.addNode(n2);

        List<Node> neighborsOfA = g.getNeighbors("a");
        List<Node> neighborsOfB = g.getNeighbors("b");

        assertEquals(1, neighborsOfA.size());
        Node first = neighborsOfA.get(0);
        String firstValue = first.getValue();
        assertEquals("b", firstValue);

        assertEquals(0, neighborsOfB.size());
    }

}
