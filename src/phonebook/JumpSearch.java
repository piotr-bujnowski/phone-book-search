package phonebook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JumpSearch {

    private final List<String> sortedNamesFromDir;
    private int matchCounter;
    private long searchTime;

    public JumpSearch(List<String> sortedNamesFromDir) {
        this.sortedNamesFromDir = sortedNamesFromDir;
        this.matchCounter = 0;
    }

    public int howManyFound(List<String> namesToFind) {
        int jumpLength = (int) Math.sqrt(sortedNamesFromDir.size());
        boolean isFound = false;
        int currentRight = 0;
        int prevRight = 0;
        searchTime = 0;

        if (sortedNamesFromDir.size() == 0 || namesToFind.size() == 0) {
            return 0;
        }

        long startTime = System.currentTimeMillis();

        /* Jump search loop */
        for (String findValue : namesToFind) {
            if (sortedNamesFromDir.get(0).contains(findValue)) {
                matchCounter++;
            }
            while (currentRight < sortedNamesFromDir.size() - 1) {

                currentRight = Math.min(sortedNamesFromDir.size() - 1, currentRight + jumpLength);

                if (sortedNamesFromDir.get(currentRight).compareTo(findValue) > 0) {
                    isFound = true;
                    break;
                }

                prevRight = currentRight;
            }

            /* Backward search in found block */
            if (isFound) {
                for (int j = currentRight; j > prevRight; j--) {
                    if (sortedNamesFromDir.get(j).contains(findValue)) {
                        matchCounter++;
                    }
                }
            }
        }

        searchTime = System.currentTimeMillis() - startTime;

        return matchCounter;
    }

    public long getSearchTime() {
        return searchTime;
    }
}