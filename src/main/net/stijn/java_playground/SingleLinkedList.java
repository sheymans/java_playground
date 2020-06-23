package net.stijn.java_playground;

import lombok.NonNull;

import java.util.Objects;
import java.util.Optional;

/**
 * A single linked list of Values.
 */
public class SingleLinkedList<V> {
    /**
     * Stores value of node with pointer to next Node.
     *
     * @param <V> the type of the value of each node
     */
    private record Node<V>(@NonNull V value, Node<V> next) { }

    /**
     * The root of the linked list.
     */
    private Node<V> root;

    /**
     * Constructor.
     */
    public SingleLinkedList() {
    }

    /**
     * Adds a value V to front of Single Linked List.
     *
     * @param value the value to add to list
     */
    public void add(V value) {
        Node<V> newNode = new Node<>(value, this.root);
        this.root = newNode;
    }

    /**
     * Gets the first value of the Single Linked List.
     *
     * @return an Optional to the first value of the Linked List or {@link Optional#empty()} when the list is empty.
     */
    public Optional<V> first() {
        if (Objects.isNull(this.root)) {
            return Optional.empty();
        } else {
            return Optional.of(this.root.value());
        }
    }
}
