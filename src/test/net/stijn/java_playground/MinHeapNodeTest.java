package net.stijn.java_playground;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinHeapNodeTest {

    @Test
    void minHeapPropertyTrue() {
        MinHeapNode child111 = new MinHeapNode(5, null, null);
        MinHeapNode child11 = new MinHeapNode(3, child111, null);
        MinHeapNode child12 = new MinHeapNode(4, null, null);
        MinHeapNode root = new MinHeapNode(2, child11, child12);
        assertTrue(root.satisfiesMinHeapProperty());
    }

    @Test
    void minHeapPropertyFalse() {
        MinHeapNode child111 = new MinHeapNode(1, null, null);
        MinHeapNode child11 = new MinHeapNode(3, child111, null);
        MinHeapNode child12 = new MinHeapNode(4, null, null);
        MinHeapNode root = new MinHeapNode(2, child11, child12);
        assertFalse(root.satisfiesMinHeapProperty());
    }
}
