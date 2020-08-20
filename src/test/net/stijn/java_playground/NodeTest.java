package net.stijn.java_playground;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class NodeTest {

    @Test
    void create() {
        Node leaf1 = new Node("aa");
        Node leaf2 = new Node("ab");
        Node root = new Node("a");
        root.addChild(leaf1);
        root.addChild(leaf2);
        assertEquals("a", root.getValue());
    }

    @Test
    void shortestPath() {
        Node a = new Node("a");
        Node a1 = new Node("a1");
        Node a2 = new Node("a2");
        a.addChild(a1);
        a1.addChild(a2);
        a.addChild(a2);

        // Expected shortest path from a to a2 is ( a a2 ) -- there's a longer one ( a a1 a2 )
        List<Node> expected = new ArrayList<>();
        expected.add(a);
        expected.add(a2);

        assertIterableEquals(expected, a.shortestPath(a2));
    }
}
