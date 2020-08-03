package net.stijn.java_playground;

import lombok.Getter;
import lombok.NonNull;

import java.util.*;
import java.util.function.Predicate;

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

    /**
     * Do depth-first search on this node.
     *
     * @param predicate the predicate to test each node for
     * @return an non-empty {@link Optional} if the node satisfying the predicate is true, an empty Optional otherwise.
     */
    public Optional<Node> dfs(Predicate<Node> predicate) {
        if (predicate.test(this)) {
            return Optional.of(this);
        }
        for (Node node : this.children) {
            Optional<Node> found = node.dfs(predicate);
            if (found.isPresent()) {
                return found;
            }
        }
        return Optional.empty();
    }
}
