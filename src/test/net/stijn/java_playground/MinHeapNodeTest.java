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

    @Test
    void addLeaf() {
        MinHeapNode child111 = new MinHeapNode(1, null, null);
        MinHeapNode child11 = new MinHeapNode(3, child111, null);
        MinHeapNode child12 = new MinHeapNode(4, null, null);
        MinHeapNode root = new MinHeapNode(2, child11, child12);

        MinHeapNode added = root.addLeaf(5);
        assertEquals(5, added.getValue());

        // to maintain completeness, it should have been added to child11 as the right child (child111 is already the left child)
        assertEquals(added, child11.getRight());

        // Add another one:
        MinHeapNode added2 = root.addLeaf(6);
        assertEquals(6, added2.getValue());
        // as child11 has 2 children, we need to add it to the next one, so as left child of child12
        assertEquals(added2, child12.getLeft());
    }

    @Test
    void bubbleUp() {
        MinHeapNode child111 = new MinHeapNode(1, null, null);
        MinHeapNode child11 = new MinHeapNode(3, child111, null);
        MinHeapNode child12 = new MinHeapNode(4, null, null);
        MinHeapNode root = new MinHeapNode(2, child11, child12);

        // child111 is 1 with parent 3, which has parent 2, so it has to bubble up entirely
        root.bubbleUp(child111);

        assertEquals(1, root.getValue());
        assertEquals(3, child111.getValue());
        assertEquals(2, child11.getValue());
    }

    @Test
    void add() {
        MinHeapNode root = new MinHeapNode(4, null, null);
        root.add(2);
        root.add(3);
        root.add(50);

        assertTrue(root.satisfiesMinHeapProperty());

        // Smallest value in tree is 2.
        assertEquals(2, root.getValue());
    }

    @Test
    void pop() {
        MinHeapNode root = new MinHeapNode(4, null, null);
        root.add(2);
        root.add(3);
        root.add(50);

        assertTrue(root.satisfiesMinHeapProperty());
        assertEquals(2, root.pop());
        assertTrue(root.satisfiesMinHeapProperty());
        assertEquals(3, root.pop());
        assertTrue(root.satisfiesMinHeapProperty());
        assertEquals(4, root.pop());
        assertTrue(root.satisfiesMinHeapProperty());
        assertEquals(50, root.getValue());

        root.add(1);
        assertTrue(root.satisfiesMinHeapProperty());
        assertEquals(1, root.pop());
        assertTrue(root.satisfiesMinHeapProperty());
    }
}
