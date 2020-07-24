package net.stijn.java_playground;

import lombok.Getter;

import java.util.Objects;

import static java.lang.Math.max;

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
    // Since adding/removing elements from MinHeap requires bubbling up and down, it's useful to keep the parent
    private MinHeapNode parent = null;

    public MinHeapNode(int value, MinHeapNode left, MinHeapNode right) {
        this.value = value;
        this.left = left;
        this.right = right;

        if (Objects.nonNull(left)) {
            this.left.parent = this;
        }
        if (Objects.nonNull(right)) {
            this.right.parent = this;
        }
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
            boolean localMinHeap = this.value <= this.left.value && this.value <= this.right.value;
            if (!localMinHeap) {
                return false;
            }
            // Each node has to satisfy the property:
            boolean leftMinHeap = this.left.satisfiesMinHeapProperty();
            if (!leftMinHeap) {
                return false;
            }

            boolean rightMinHeap = this.right.satisfiesMinHeapProperty();
            return rightMinHeap;
        }

        return false;
    }

    /**
     * Removes minimum from heap.
     *
     * @return minimum if it exists.
     */
    public int popMin() {
        // in a heap the root has the minimum value.
        int min = this.value;

        // TODO

        return min;
    }

    /**
     * Give the tail of the this node, i.e., the node to which you would add either a left or right node to maintain completeness of this node.
     *
     * Recall that a complete tree is a tree with each level filled except possibly the last one, which is filled from
     * left to right.
     *
     * @return the {@link MinHeapNode} to which to add the last element.
     *
     */
    protected MinHeapNode getTail() {
        if (this.isLeaf()) {
            return this;
        }

        if (Objects.isNull(this.right) && Objects.nonNull(this.left)) {
            return this;
        }

        if (Objects.nonNull(this.right) && Objects.nonNull(this.left)) {
            MinHeapNode candidate1 = this.left.getTail();
            if (Objects.isNull(this.parent)) {
                return candidate1;
            }
            if (Objects.isNull(this.parent.right)) {
                // impossible would mean the tree is not complete
                throw new IllegalStateException();
            }
            MinHeapNode candidate2 = this.parent.right.getTail();
            if (candidate1.getDepth() < candidate2.getDepth()) {
                return candidate1;
            } else {
                return candidate2;
            }
        }

        return this;
    }

    /**
     * Calculates the depth of this node.
     *
     * @return the max depth of the left and right tree + 1
     */
    protected int getDepth() {
        int leftDepth = 0;
        int rightDepth = 0;

        if (this.isLeaf()) {
            return 0;
        }

        if (Objects.nonNull(this.left)) {
            leftDepth = this.left.getDepth();
        }
        if (Objects.nonNull(this.right)) {
            rightDepth = this.right.getDepth();
        }

        int maxSubTreeDepth = max(leftDepth, rightDepth);
        return maxSubTreeDepth + 1;
    }

    /**
     * Add a leaf in the right location to ensure the tree stays complete.
     *
     * @param value the value of the lead node to add.
     */
    protected void addLeaf(int value) {
        MinHeapNode node = new MinHeapNode(value, null, null);
        MinHeapNode tail = this.getTail();

        if (Objects.isNull(tail.left)) {
            tail.left = node;
            node.parent = tail.left;
        }
        if (Objects.isNull(tail.right)) {
            tail.right = node;
            node.parent = tail.right;
        }
    }
}
