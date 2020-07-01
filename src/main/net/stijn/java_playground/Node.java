package net.stijn.java_playground;

import lombok.Getter;
import lombok.NonNull;

/**
 * A very simple Node with String value.
 */
@Getter
public class Node {
    private String value;
    private Node[] children;

    /**
     * Constructor.
     *
     * @param value the value of the Node
     * @param children the children of the Node.
     */
    public Node(@NonNull String value, Node[] children) {
        this.value = value;
        this.children = children;
    }
}
