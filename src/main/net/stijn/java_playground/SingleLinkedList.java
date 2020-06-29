package net.stijn.java_playground;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
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
    private class Node<V> {
        private V value;
        private Node<V> next;

        public Node(@NonNull V value, Node<V> next) {
            this.value = value;
            this.next = next;
        }
    }

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
            return Optional.of(this.root.value);
        }
    }

    /**
     * Finds the first value in the list and returns the index that it found it, or -1 if not found.
     *
     * @param v the value to find in list
     * @return the first index it finds v at, or -1 if it is not present.
     */
    public int find(@NonNull V v) {
        Node<V> current = this.root;
        int foundIndex = 0;

        while (!Objects.isNull(current)) {
            if (current.value.equals(v)) {
                return foundIndex;
            }
            current = current.next;
            ++foundIndex;
        }

        return -1;
    }

    /**
     * Delete all nodes with a value.
     *
     * @param v the value to delete.
     */
    public void delete(@NonNull V v) {
        Node<V> current = this.root;
        Node<V> previous = null;

        while (!Objects.isNull(current)) {
            if (current.value.equals(v)) {
                if (Objects.isNull(previous)) {
                    // We're actually deleting the root.
                    this.root = this.root.next;
                } else {
                    // Note that this is why we do not store the inner Node as a record,
                    // we're changing nodes (and a record makes things like `next` final as it's supposed
                    // to be immutable.
                    previous.next = current.next;
                }
            } else {
                // Don't move previous if you're deleting current (you do not want previous
                // to point to a deleted node.)
                previous = current;
            }
            current = current.next;
        }
    }
}
