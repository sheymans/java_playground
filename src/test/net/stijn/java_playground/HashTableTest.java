package net.stijn.java_playground;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @Test
    void put() {
        HashTable hashTable = new HashTable(10);
        hashTable.put("key1", "value1");
        String printed = hashTable.toString();
        assertEquals("0: empty | 1: empty | 2: empty | 3: empty | 4: empty | 5: empty | 6: empty | 7: empty | 8: [value1] | 9: empty | ",
                     printed,
                     "put did not put key1/value1 in hashtable");
    }

    @Test
    void get() {
    }
}
