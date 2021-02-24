package phonebook;

import java.util.Collections;
import java.util.List;

public class QuickSort {

    private long sortTime;

    public void sort(List<String> list, int left, int right) {
        long startTime = System.currentTimeMillis();
        sortTime = 0;

        if (left < right) {
            int pivotIndex = partition(list, left, right);
            sort(list, left, pivotIndex - 1);
            sort(list, pivotIndex + 1, right);
        }

        sortTime = System.currentTimeMillis() - startTime;
    }

    private int partition(List<String> list, int left, int right) {
        String pivot = list.get(right);
        int partitionIndex = left;


        for (int i = left; i < right; i++) {
            if (list.get(i).compareTo(pivot) < 0) {
                Collections.swap(list, i, partitionIndex);
                partitionIndex++;
            }
        }

        Collections.swap(list, partitionIndex, right);

        return partitionIndex;
    }

    public long getSortTime() {
        return sortTime;
    }
}
