package net.stijn.java_playground;

import lombok.NonNull;
import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * HashTable with Strings as Keys and Strings as Values. Implemented as an Array of Linked Lists.
 */
public class HashTable {
    /**
     * Array of LinkedList of Pairs to store table in. Pairs cause it could be that you're storing different keys
     * in the same list (when hash of key is same).
     */
    private List<Pair<String, String>>[] table;

    /**
     * Constructor.
     *
     * @param size the size to of the underlying array supporting the hash table.
     */
    public HashTable(int size) {
        this.table = new LinkedList[size];
    }

    /**
     * Puts a key/value pair in HashTable.
     *
     * @param key   The key to put in the table.
     * @param value The value to put in the table.
     */
    public void put(@NonNull String key, @NonNull String value) {
        int hash = this.hash(key);
        int indexInArray = hash % this.table.length;
        List<Pair<String, String>> listOnIndex = this.table[indexInArray];
        if (Objects.isNull(listOnIndex)) {
            List<Pair<String,String>> newList = new LinkedList<>();
            newList.add(Pair.of(key, value));
            this.table[indexInArray] = newList;
        } else {
            listOnIndex.add(Pair.of(key, value));
        }
    }

    /**
     * Gets a value based on non-null key.
     *
     * @param key the key for which to find the value
     * @return an Optional with the String value matches key, or, if not found, an empty Optional.
     */
    public Optional<String> get(@NonNull String key) {
        int hash = this.hash(key);
        int indexInArray = hash % this.table.length;

        List<Pair<String, String>> listOnIndex = this.table[indexInArray];
        Optional<String> found = listOnIndex.stream()
                .filter(pair -> key.equals(pair.getLeft()))
                .map(Pair::getRight)
                .findFirst();
        return found;
    }

    /**
     * Calculates hash for {@link String}. We're just using {@link String#hashCode()}.
     *
     * @param key the {@link String} to calculate the hash for.
     * @return an integer representing the key.
     */
    public int hash(@NonNull String key) {
        return key.hashCode();
    }

    /**
     * Overrides the toString for pretty-printing.
     *
     * @return pretty-printed {@link String} of HashTable. For example, something like
     * {@code 0: empty | 1: empty | 2: [value1] | }
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.table.length; ++i) {
            List<Pair<String,String>> valueList = this.table[i];
            String valueListRepresentation = "empty";
            if (Objects.nonNull(valueList)) {
                valueListRepresentation = valueList.toString();
            }

            builder.append(i);
            builder.append(": " + valueListRepresentation + " | ");
        }
        return builder.toString();
    }
}
