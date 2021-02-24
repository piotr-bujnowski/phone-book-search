package phonebook;

import java.time.Duration;
import java.util.List;

public class LinearSearch {

    private static long searchTime;
    private List<String> listToSearch;

    public LinearSearch(List<String> listToSearch) {
        this.listToSearch = listToSearch;
    }

    public int howManyFound(List<String> findList) {
        int foundCount = 0;
        searchTime = 0;
        long startTime = System.currentTimeMillis();

        for(String nameToFind : findList) {
            for (String nameDir : listToSearch) {
                if (nameDir.equals(nameToFind)) {
                    foundCount++;
                }
            }
        }
        searchTime = System.currentTimeMillis() - startTime;

        return foundCount;
    }

    public static long getSearchTime() {
        return searchTime;
    }
}
