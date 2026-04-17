import java.util.*;

public class RiskThresholdLookup {

    // 🔹 Linear Search (unsorted)
    public static int linearSearch(int[] arr, int target) {
        int comparisons = 0;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear Comparisons: " + comparisons);
                return i;
            }
        }

        System.out.println("Linear Comparisons: " + comparisons);
        return -1;
    }

    // 🔹 lower_bound → first index where value >= target
    public static int lowerBound(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] < target) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    // 🔹 upper_bound → first index where value > target
    public static int upperBound(int[] arr, int target) {
        int low = 0, high = arr.length;

        while (low < high) {
            int mid = (low + high) / 2;

            if (arr[mid] <= target) low = mid + 1;
            else high = mid;
        }
        return low;
    }

    // 🔹 Floor (largest ≤ target)
    public static Integer floor(int[] arr, int target) {
        int idx = lowerBound(arr, target);

        if (idx < arr.length && arr[idx] == target) return arr[idx];
        if (idx == 0) return null;

        return arr[idx - 1];
    }

    // 🔹 Ceiling (smallest ≥ target)
    public static Integer ceiling(int[] arr, int target) {
        int idx = lowerBound(arr, target);

        if (idx == arr.length) return null;
        return arr[idx];
    }

    public static void main(String[] args) {

        int[] risks = {10, 25, 50, 100};
        int target = 30;

        // 🔹 Linear Search (unsorted case)
        int lin = linearSearch(risks, target);
        System.out.println("Linear Search Result: " + lin);

        // 🔹 Binary Search Variants
        int lb = lowerBound(risks, target);
        int ub = upperBound(risks, target);

        System.out.println("\nLower Bound Index: " + lb);
        System.out.println("Upper Bound Index: " + ub);

        // 🔹 Floor & Ceiling
        Integer f = floor(risks, target);
        Integer c = ceiling(risks, target);

        System.out.println("Floor(" + target + "): " + f);
        System.out.println("Ceiling(" + target + "): " + c);
    }
}