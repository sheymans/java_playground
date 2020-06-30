package net.stijn.java_playground;

import lombok.NonNull;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class Stack<T> {
    private List<T> stackList;

    /**
     * Constructor.
     */
    public Stack() {
        this.stackList = new LinkedList<>();
    }

    /**
     * Pops element from Stack
     *
     * @return an {@link Optional} of the popped element
     */
    public Optional<T> pop() {
        Optional<T> lastElement = this.getLastElement();
        int indexOfLast = stackList.size() - 1;
        stackList.remove(indexOfLast);
        return lastElement;
    }

    /**
     * Pushes item to Stack.
     *
     * @param item non-null item to be pushed to stack
     */
    public void push(@NonNull T item) {
        stackList.add(item);
    }

    /**
     * Look at item that would be returned by pop on Stack.
     *
     * @return the most recent item inserted in Stack
     */
    public Optional<T> peek() {
        return this.getLastElement();
    }

    /**
     * Gets the last element added in Stack.
     *
     * @return the last element added in Stack or Optional.empty() if the Stack is empty.
     */
    private Optional<T> getLastElement() {
        if (stackList.isEmpty()) {
            return Optional.empty();
        }
        else {
            // Note that the underlying {@link List::add} add elements to the back, so when popping we need to pop
            // from back.
            int indexOfLast = stackList.size() - 1;
            T lastElement = stackList.get(indexOfLast);
            return Optional.of(lastElement);
        }
    }
}
