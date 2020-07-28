package net.stijn.java_playground;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
