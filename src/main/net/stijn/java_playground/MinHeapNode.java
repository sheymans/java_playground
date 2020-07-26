package net.stijn.java_playground;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Math.max;

/**
 * A MinHeap, implemented just as MinHeapNode to avoid doing all the unnecessary wrapping (with Nodes as inner classes).
 *
 * Conclusion of implementing a MinHeapNode like this: DON'T DO IT. An implementation based on Arrays where you encode
 * the tree in an array is simpler.
 *
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
    // use as stack (so we can always get the last added node)
    final private ArrayList<MinHeapNode> childrenAddedInOrder = new ArrayList<>();

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
    public int pop() {
        // in a heap the root has the minimum value.
        int min = this.value;
        if (this.childrenAddedInOrder.isEmpty()) {
            // you've added nothing, this is the only node.
            // that's a bit weird, as things should evaporate
            // let's not do anything; consider throwing an exception
            return min;
        }

        int indexOfLastAdded = childrenAddedInOrder.size() - 1;
        MinHeapNode lastAdded = this.childrenAddedInOrder.get(indexOfLastAdded);
        this.value = lastAdded.value;
        // remove lastAdded from the tree
        MinHeapNode parent = lastAdded.parent;
        if (Objects.nonNull(parent)) {
            if (lastAdded.equals(parent.right)) {
                parent.right = null;
            } else {
                parent.left = null;
            }
        }
        this.childrenAddedInOrder.remove(indexOfLastAdded);
        this.bubbleDown(this);

        return min;
    }

    /**
     * Add a value by adding it as a leaf and then bubbling it up (as in min heap).
     *
     * @param value the value to add
     */
    public void add(int value) {
        MinHeapNode added = this.addLeaf(value);
        this.childrenAddedInOrder.add(added);
        this.bubbleUp(added);
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
     *
     * @return the leaf node that was added
     */
    protected MinHeapNode addLeaf(int value) {
        MinHeapNode node = new MinHeapNode(value, null, null);
        MinHeapNode tail = this.getTail();

        if (Objects.isNull(tail.left)) {
            tail.left = node;
            node.parent = tail;
            return node;
        }
        if (Objects.isNull(tail.right)) {
            tail.right = node;
            node.parent = tail;
            return node;
        }

        return node;
    }

    /**
     * Bubble up the node to make sure the min heap property remains satisfied.
     *
     * @param node the node to bubble up (note that we only bubble up the values, we keep the tree structure).
     */
    protected void bubbleUp(MinHeapNode node) {
        // we have the root:
        if (Objects.isNull(node.parent)) {
            return;
        }

        MinHeapNode parent = node.parent;
        int parentValue = parent.getValue();
        int nodeValue = node.getValue();

        // All good, the parent is smaller than the child
        if (parentValue <= nodeValue) {
            return;
        }

        // Swap parent and node
        node.value = parentValue;
        parent.value = nodeValue;
        // and bubble up
        this.bubbleUp(parent);
    }

    /**
     * Bubble down the node.
     *
     * @param node the node to bubble down (the value of the node)
     */
    protected void bubbleDown(MinHeapNode node) {
        // we have a leaf, so done bubbling
        if (node.isLeaf()) {
            return;
        }

        int valueToBubble = node.value;

        MinHeapNode left = node.left;
        MinHeapNode right = node.right;

        if (Objects.isNull(right)) {
            // left can't be null otherwise it's a leaf which we catch before
            if (valueToBubble <= left.value) {
                // all in place
                return;
            }
            node.value = left.value;
            left.value = valueToBubble;
            this.bubbleDown(left);
            return;
        }

        // both left and right are non-null
        if (left.value < valueToBubble && left.value <= right.value) {
            // bubble down left
            node.value = left.value;
            left.value = valueToBubble;
            this.bubbleDown(left);
            return;
        }

        if (right.value < valueToBubble && right.value <= left.value) {
            // bubble down right
            node.value = right.value;
            right.value = valueToBubble;
            this.bubbleDown(right);
            return;
        }

        // don't bubble, things are in place
    }
}
