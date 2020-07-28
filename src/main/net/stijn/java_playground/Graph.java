package net.stijn.java_playground;

import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
}
