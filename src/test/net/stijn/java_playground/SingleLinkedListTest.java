package net.stijn.java_playground;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleLinkedListTest {

    @Test
    void add() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("first element");
        assertEquals("first element", list.first().orElse("nothing"));
    }
}
