package net.stijn.java_playground;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NodeTest {

    @Test
    void create() {
        Node leaf1 = new Node("aa", null);
        Node leaf2 = new Node("ab", null);
        Node root = new Node("a", new Node[]{leaf1, leaf2});
        assertEquals("a", root.getValue());
    }
}
