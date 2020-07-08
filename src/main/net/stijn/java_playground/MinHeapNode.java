package net.stijn.java_playground;

import lombok.Getter;

import java.util.Objects;

/**
 * A MinHeap, implemented just as MinHeapNode to avoid doing all the unnecessary wrapping (with Nodes as inner classes).
 * <p>
 * Recall that a MinHeap is a complete binary tree where every node is smaller than its children.
 */
@Getter
public class MinHeapNode {
    private int value;
    private MinHeapNode left;
    private MinHeapNode right;

    public MinHeapNode(int value, MinHeapNode left, MinHeapNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    /**
     * Is the node a leaf, i.e., has no children.
     *
     * @return true if both children are null.
     */
    public boolean isLeaf() {
        return Objects.isNull(this.left) && Objects.isNull(this.right);
    }

    /**
     * Does the MinHeapNode satisfy the min heap property: every node is smaller than its children.
     *
     * @return true if every node is smaller than its children.
     */
    public boolean satisfiesMinHeapProperty() {

        // Node is leaf (both children are null):
        if (this.isLeaf()) {
            return true;
        }

        // Node has null right child but non-null left child:
        if (Objects.isNull(this.right) && Objects.nonNull(this.left)) {
            return this.value <= this.left.value;
        }

        // This is not possible as a min-heap is a complete tree: (Objects.nonNull(this.right) && Objects.isNull(this.left))
        // so all levels are filled except the last one, which is filled from left to right, so something left cannot be null when right is null.

        // Both children are non-null:
        if (Objects.nonNull(this.right) && Objects.nonNull(this.left)) {
            return this.value <= this.left.value && this.value <= this.right.value;
        }

        return false;
    }
}
