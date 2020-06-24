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

    @Test
    void addMultiple() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("first element");
        list.add("second element");
        // Recall that we add from front:
        assertEquals("second element", list.first().orElse("nothing"));
    }

    @Test
    void findNotPresent() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("el1");
        list.add("el2");
        assertEquals(-1, list.find("el3"), "el3 is not in list");
    }

    @Test
    void findPresent() {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("el1");
        list.add("el2");
        // Recall that we add from front:
        assertEquals(0, list.find("el2"), "el2 is at first place");
        assertEquals(1, list.find("el1"), "el1 is at second place");
    }
}
