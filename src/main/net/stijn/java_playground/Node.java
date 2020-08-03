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
     * @param visited keep a set of nodes that were already visited (for example, in other calls to dfs as part of a graph search).
     * @return an non-empty {@link Optional} if the node satisfying the predicate is true, an empty Optional otherwise.
     */
    public Optional<Node> dfs(Predicate<Node> predicate, Set<Node> visited) {
        if (visited.contains(this)) {
            return Optional.empty();
        }
        visited.add(this);
        if (predicate.test(this)) {
            return Optional.of(this);
        }
        for (Node node : this.children) {
            Optional<Node> found = node.dfs(predicate, visited);
            if (found.isPresent()) {
                return found;
            }
        }
        return Optional.empty();
    }

    /**
     * Breadth-first search on a node. Notice how this uses a queue.
     *
     * @param predicate the test for the nodes
     * @param visited keep a set of nodes that were already visited (for example, in other calls to dfs as part of a graph search).
     * @returnan non-empty {@link Optional} if the node satisfying the predicate is true, an empty Optional otherwise.
     */
    public Optional<Node> bfs(Predicate<Node> predicate, Set<Node> visited) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(this);

        while (!queue.isEmpty()) {
            Node first = queue.remove();
            if (visited.contains(first)) {
                continue;
            }
            visited.add(first);
            if (predicate.test(first)) {
                return Optional.of(first);
            }
            // Collections::addAll appends all of the elements in first.children to the end of the queue, in the order that appear in first.children.
            queue.addAll(first.children);
        }
        return Optional.empty();
    }
}
