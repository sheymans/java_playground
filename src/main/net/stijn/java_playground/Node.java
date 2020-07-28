package net.stijn.java_playground;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A very simple Node with String value.
 */
@Getter
public class Node {
    @NonNull
    final private String value;
    @NonNull
    final private List<Node> children = new ArrayList<>();

    /**
     * Constructor without children.
     *
     * @param value the value of the Node.
     */
    public Node(@NonNull String value) {
        this.value = value;
    }

    /**
     * Add a child node.
     *
     * @param child the child to add.
     */
    public void addChild(@NonNull Node child) {
        this.children.add(child);
    }
}
