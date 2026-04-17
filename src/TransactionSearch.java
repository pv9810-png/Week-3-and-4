import java.util.*;

public class TransactionSearch {

    // 🔹 Linear Search (first occurrence + comparisons)
    public static int linearSearch(String[] arr, String target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear Search Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear Search Comparisons: " + comparisons);
        return -1;
    }

    // 🔹 Binary Search (any one occurrence)
    public static int binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;

        while (low <= high) {
            comparisons++;
            int mid = (low + high) / 2;

            int cmp = arr[mid].compareTo(target);

            if (cmp == 0) {
                System.out.println("Binary Search Comparisons: " + comparisons);
                return mid;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary Search Comparisons: " + comparisons);
        return -1;
    }

    // 🔹 Count occurrences (after binary search)
    public static int countOccurrences(String[] arr, String target) {
        int count = 0;
        for (String s : arr) {
            if (s.equals(target)) count++;
        }
        return count;
    }

    public static void main(String[] args) {

        // Input (unsorted)
        String[] logs = {"accB", "accA", "accB", "accC"};

        // 🔹 Linear Search (works on unsorted)
        int linearIndex = linearSearch(logs, "accB");
        System.out.println("Linear first accB index: " + linearIndex);

        // 🔹 Sort for Binary Search
        Arrays.sort(logs);

        System.out.println("\nSorted Logs:");
        System.out.println(Arrays.toString(logs));

        // 🔹 Binary Search (requires sorted array)
        int binaryIndex = binarySearch(logs, "accB");
        System.out.println("Binary accB index: " + binaryIndex);

        // 🔹 Count occurrences
        int count = countOccurrences(logs, "accB");
        System.out.println("Count of accB: " + count);
    }
}