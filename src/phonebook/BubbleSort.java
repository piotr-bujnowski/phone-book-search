package phonebook;

import java.util.ArrayList;

public class BubbleSort {

    private ArrayList<String> list;
    private long sortTime;
    private boolean isTooLong;

    public BubbleSort(ArrayList<String> listToSort) {
        this.list = listToSort;
        isTooLong = false;
    }

    public void sortAlphabetically() throws InterruptedException {

        long startBubbleSortTime = System.currentTimeMillis();

        for (int i = 0; i < this.list.size() - 1; i++) {
            for (int j = 0; j < this.list.size() - i - 1; j++) {

                if (this.list.get(j).charAt(0) > this.list.get(j + 1).charAt(0)) {
                    String temp = this.list.get(j);
                    this.list.set(j, this.list.get(j + 1));
                    this.list.set(j + 1, temp);
                    sortTime = System.currentTimeMillis() - startBubbleSortTime;

                    if (sortTime > LinearSearch.getSearchTime()) {
                        isTooLong = true;
                        return;
                    }
                }
            }
        }
    }

    public ArrayList<String> getList() {
        return list;
    }

    public long getSortTime() {
        return sortTime;
    }

    public boolean isTooLong() {
        return isTooLong;
    }
}
