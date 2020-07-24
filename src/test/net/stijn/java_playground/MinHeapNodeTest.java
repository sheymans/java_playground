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

    @Test
    void getDepth() {
        MinHeapNode child111 = new MinHeapNode(1, null, null);
        MinHeapNode child11 = new MinHeapNode(3, child111, null);
        MinHeapNode child12 = new MinHeapNode(4, null, null);
        MinHeapNode root = new MinHeapNode(2, child11, child12);
        assertEquals(2, root.getDepth());
        assertEquals(1, child11.getDepth());
        assertEquals(0, child111.getDepth());
    }

    @Test
    void getTail() {
        MinHeapNode child111 = new MinHeapNode(1, null, null);
        MinHeapNode child11 = new MinHeapNode(3, child111, null);
        MinHeapNode child12 = new MinHeapNode(4, null, null);
        MinHeapNode root = new MinHeapNode(2, child11, child12);
        assertEquals(child11, root.getTail());
        assertEquals(child11, child11.getTail());
        assertEquals(child111, child111.getTail());
        assertEquals(child12, child12.getTail());
    }
}
