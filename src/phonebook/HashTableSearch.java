package phonebook;

import com.sun.jdi.connect.Connector;

import java.util.*;

public class HashTableSearch {

    private Hashtable<Integer, String> hashTable;
    private List<String> directory;
    private long searchTime;
    private long creationTime;

    public HashTableSearch(List<String> directory) {
        this.hashTable = new Hashtable<>(directory.size());
        this.directory = directory;
    }

    public int search(List<String> findList) {
        int count = 0;
        searchTime = 0;


        long startTime = System.currentTimeMillis();

        for (String name : findList) {
            if (hashTable.contains(name)) {
                count++;
            }
        }
        searchTime = System.currentTimeMillis() - startTime;

        return count;
    }

    public void createHashTable() {
        creationTime = 0;

        long startTime = System.currentTimeMillis();
        for (String s : directory) {
            hashTable.put(s.hashCode(), s);
        }
        creationTime = System.currentTimeMillis() - startTime;
    }

    public Map<Integer, String> getHashTable() {
        return hashTable;
    }

    public long getSearchTime() {
        return searchTime;
    }

    public long getCreationTime() {
        return creationTime;
    }

    public List<String> getDirectory() {
        return directory;
    }
}
