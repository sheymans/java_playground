package net.stijn.java_playground;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class StackTest {

    @Test
    void pop() {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        Optional<String> popped = stack.pop();
        assertTrue(popped.isPresent());
        // last added item was "b" so that's the one we expect to be popped first.
        assertEquals("b", popped.get());
    }

    @Test
    void peek() {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        Optional<String> peeked = stack.peek();
        assertTrue(peeked.isPresent());
        // last added item was "b" so that's the one we expect to be peeked.
        assertEquals("b", peeked.get());

        // do it again and you should see the same:
        Optional<String> peekedNext = stack.peek();
        assertTrue(peekedNext.equals(peeked));
    }
}
