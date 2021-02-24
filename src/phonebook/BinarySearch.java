package phonebook;

import java.util.List;

public class BinarySearch {

    private long searchTime;

    public int search(List<String> list, List<String> toFind, int left, int right) {
        searchTime = 0;
        int countFound = 0;
        long startTime = System.currentTimeMillis();

        for (String name : toFind) {
            left = 0;
            right = list.size() - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2; // The index of the middle element

                if (name.equals(list.get(mid))) {
                    countFound++;
                    break;
                } else if (name.compareTo(list.get(mid)) < 0) {
                    right = mid - 1; // Move/Check left list
                } else {
                    left = mid + 1;  // Move/Check right list
                }
            }
        }
        searchTime = System.currentTimeMillis() - startTime;
        return countFound;
    }

    public long getSearchTime() {
        return searchTime;
    }
}
