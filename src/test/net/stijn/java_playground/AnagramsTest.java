package net.stijn.java_playground;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnagramsTest {

    @Test
    void alphabetize() {
        assertEquals("abc", Anagrams.alphabetize("cab"));
    }
}