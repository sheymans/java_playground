package net.stijn.java_playground;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Graph {

    @Getter
    private class GraphNode {
        private String name;
        final private List<GraphNode> children = new ArrayList<>();

        private GraphNode(@NonNull String name) {
            this.name = name;
        }

        private void addChild(GraphNode child) {
            this.children.add(child);
        }

        private boolean isChild(@NonNull String childname) {
            return this.children.stream()
                    .map(GraphNode::getName)
                    .anyMatch(name -> name.equals(childname));
        }

    }

    final private List<GraphNode> nodes =  new ArrayList<>();

    // No constructor, default will be provided.

    public void addNode(@NonNull String name) {
        GraphNode node = new GraphNode(name);
        nodes.add(node);
        // throw exception/make no-op if node already exists
    }

    public Optional<GraphNode> getNode(@NonNull String name) {
        return this.nodes.stream()
                .filter(node -> node.getName().equals(name))
                .findFirst();
    }

    public void addEdge(@NonNull String name1, @NonNull String name2) {
        Optional<GraphNode> node1 = this.getNode(name1);
        Optional<GraphNode> node2 = this.getNode(name2);

        node1.ifPresent(n1 -> node2.ifPresent(n1::addChild));
    }
}
