package net.stijn.java_playground;

import lombok.NonNull;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Predicate;

public class Graph {

    final private List<Node> nodes = new ArrayList<>();

    /**
     * Adds a node to the nodes.
     *
     * @param node the node to add.
     */
    public void addNode(@NonNull Node node) {
        this.nodes.add(node);
    }

    /**
     * Gets neighbors of a node with value.
     *
     * @param value the value to search for
     * @return the list of neighbors or the empty list if you did not find any.
     */
    public List<Node> getNeighbors(@NonNull String value) {
        Optional<Node> valueNode = this.nodes.stream()
                .filter(node -> node.getValue().equals(value))
                .findFirst();
        if (valueNode.isPresent()) {
            return valueNode.get().getChildren();
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Depth first search on the graph, just goes through its list of nodes and does a Node DFS on them.
     *
     * @param predicate the predicate to test for truth.
     * @return an non-empty {@link Optional} if the node satisfying the predicate is true, an empty Optional otherwise.
     */
    public Optional<Node> dfs(Predicate<Node> predicate) {
        Set<Node> visited = new HashSet<>();
        for (Node node: this.nodes) {
            Optional<Node> found = node.dfs(predicate, visited);
            if (found.isPresent()) {
                return found;
            }
        }
        return Optional.empty();
    }

    /**
     * Breadth first search on the graph.
     *
     * @param predicate the predicate to test for truth.
     * @return an non-empty {@link Optional} if the node satisfying the predicate is true, an empty Optional otherwise
     */
    public Optional<Node> bfs(Predicate<Node> predicate) {
        Set<Node> visited = new HashSet<>();
        for (Node node: this.nodes) {
            Optional<Node> found = node.bfs(predicate, visited);
            if (found.isPresent()) {
                return found;
            }
        }
        return Optional.empty();
    }

    // TODO generalize above to a `find` with a strategy.
}
