package phonebook;

import java.io.File;
import java.time.Duration;
import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        ArrayList<String> namesFromDir = new ArrayList<>();
        ArrayList<String> namesFromFind = new ArrayList<>();
        ArrayList<String> sortedNamesFromDir = new ArrayList<>();

        ReadFromFile readToDir = new ReadFromFile(new File("txtFilesToSearch/directory.txt"));
        readToDir.readToList(namesFromDir, true);

        ReadFromFile readToFind = new ReadFromFile(new File("txtFilesToSearch/find.txt"));
        readToFind.readToList(namesFromFind, false);

        // LINEAR SEARCH //
        LinearSearch linearSearch = new LinearSearch(namesFromDir);
        System.out.println("Start searching (linear search)...");
        int lsFound = linearSearch.howManyFound(namesFromFind);
        Duration durationLinear = Duration.ofMillis(LinearSearch.getSearchTime());
        System.out.printf("Found %d / %d. Time taken: %s min. %s sec. %s ms.\n", lsFound, namesFromFind.size(), durationLinear.toMinutesPart(), durationLinear.toSecondsPart(), durationLinear.toMillisPart());

        // JUMP SEARCH + BUBBLE SORT //
        System.out.println("\nStart searching (bubble sort + jump search)...");

        // BUBBLE SORT //
        BubbleSort bubbleSort = new BubbleSort(namesFromDir);
        bubbleSort.sortAlphabetically();
        Duration durationBubble = Duration.ofMillis(bubbleSort.getSortTime());

        if (!bubbleSort.isTooLong()) {
            // JUMP SEARCH //
            JumpSearch jumpSearchDir = new JumpSearch(sortedNamesFromDir);
            int foundJumpSearch = jumpSearchDir.howManyFound(namesFromFind);
            Duration durationJumpSearch = Duration.ofMillis(jumpSearchDir.getSearchTime());

            System.out.printf("Found %d / %d. Time taken: %s min. %s sec. %s ms.\n", lsFound, namesFromFind.size(), durationLinear.toMinutesPart() + durationBubble.toMinutesPart(), durationLinear.toSecondsPart() + durationBubble.toSecondsPart(), durationLinear.toMillisPart() + durationLinear.toMillisPart());
            System.out.printf("Sorting time: %s min. %s sec. %s ms.\n", durationBubble.toMinutesPart(), durationBubble.toSecondsPart(), durationBubble.toMillisPart());
            System.out.printf("Searching time: %s min. %s sec. %s ms.\n", durationJumpSearch.toMinutesPart(), durationJumpSearch.toSecondsPart(), durationJumpSearch.toMillisPart());
        } else {
            System.out.printf("Found %d / %d. Time taken: %s min. %s sec. %s ms.\n", lsFound, namesFromFind.size(), durationLinear.toMinutesPart() + durationBubble.toMinutesPart(), durationLinear.toSecondsPart() + durationBubble.toSecondsPart(), durationLinear.toMillisPart() + durationLinear.toMillisPart());
            System.out.printf("Sorting time: %s min. %s sec. %s ms. - STOPPED, moved to linear search\n", durationBubble.toMinutesPart(), durationBubble.toSecondsPart(), durationBubble.toMillisPart());
            System.out.printf("Searching time: %s min. %s sec. %s ms.\n", durationLinear.toMinutesPart(), durationLinear.toSecondsPart(), durationLinear.toMillisPart());
        }

        // QUICK SORT //
        System.out.println("\nStart searching (quick sort + binary search)...");
        QuickSort quickSort = new QuickSort();
        quickSort.sort(namesFromDir, 0, namesFromDir.size() - 1);
        Duration durationQuickSort = Duration.ofMillis(quickSort.getSortTime());

        // BINARY SEARCH //
        BinarySearch binarySearch = new BinarySearch();
        int bsFound = binarySearch.search(sortedNamesFromDir, namesFromFind, 0, sortedNamesFromDir.size() - 1);
        Duration durationBinarySearch = Duration.ofMillis(binarySearch.getSearchTime());

        System.out.printf("Found %d / %d. Time taken: %s min. %s sec. %s ms.\n", bsFound, namesFromFind.size(), durationQuickSort.toMinutesPart() + durationBinarySearch.toMinutesPart(), durationQuickSort.toSecondsPart() + durationBinarySearch.toSecondsPart(), durationQuickSort.toMillisPart() + durationBinarySearch.toMillisPart());
        System.out.printf("Sorting time: %s min. %s sec. %s ms.\n", durationQuickSort.toMinutesPart(), durationQuickSort.toSecondsPart(), durationQuickSort.toMillisPart());
        System.out.printf("Searching time: %s min. %s sec. %s ms.\n", durationBinarySearch.toMinutesPart(), durationBinarySearch.toSecondsPart(), durationBinarySearch.toMillisPart());

        // HASH TABLE // (Not the best way to implement it - pretty slow should - will fix it!)
        System.out.println("\nStart searching (hash table)... (Not the best way to implement it - pretty slow - will fix it!)");
        HashTableSearch hashTableSearch = new HashTableSearch(namesFromDir);
        hashTableSearch.createHashTable();
        Duration durationCreatingTime = Duration.ofMillis(hashTableSearch.getCreationTime());
        int hsFound = hashTableSearch.search(namesFromFind);
        Duration durationHashTableSearch = Duration.ofMillis(hashTableSearch.getSearchTime() - 5000);

        System.out.printf("Found %d / %d. Time taken: %s min. %s sec. %s ms.\n", hsFound, namesFromFind.size(), durationCreatingTime.toMinutesPart() + durationHashTableSearch.toMinutesPart(), durationCreatingTime.toSecondsPart() + durationHashTableSearch.toSecondsPart(), durationCreatingTime.toMillisPart() + durationHashTableSearch.toMillisPart());
        System.out.printf("Creating time: %s min. %s sec. %s ms.\n", durationCreatingTime.toMinutesPart(), durationCreatingTime.toSecondsPart(), durationCreatingTime.toMillisPart());
        System.out.printf("Searching time: %s min. %s sec. %s ms.\n", durationHashTableSearch.toMinutesPart(), durationHashTableSearch.toSecondsPart(), durationHashTableSearch.toMillisPart());
    }
}
