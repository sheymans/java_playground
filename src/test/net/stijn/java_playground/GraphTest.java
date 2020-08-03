package net.stijn.java_playground;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void dfs() {
        Node n1 = new Node("a");
        Node n11 = new Node("b");
        Node n12 = new Node("c");
        Node n121 = new Node("d");
        Node n122 = new Node("e");
        n1.addChild(n11);
        n1.addChild(n12);
        n12.addChild(n121);
        n12.addChild(n122);

        Graph g = new Graph();
        g.addNode(n1);
        g.addNode(n11);
        g.addNode(n12);
        g.addNode(n121);
        g.addNode(n122);

        Optional<Node> nodeE = g.dfs(node -> node.getValue().equals("e"));
        assertTrue(nodeE.isPresent(), "we found a node");
        assertEquals("e", nodeE.get().getValue(), "the node's value is e");

        Optional<Node> nodeF = g.dfs(node -> node.getValue().equals("f"));
        assertFalse(nodeF.isPresent(), "we found did find a node");
    }

}
