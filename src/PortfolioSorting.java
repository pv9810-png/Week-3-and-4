import java.util.*;

class Asset {
    String name;
    double returnRate;
    double volatility;

    public Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return name + ":" + returnRate + "%";
    }
}

public class PortfolioSorting {

    // 🔹 Merge Sort (Ascending by returnRate - Stable)
    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public static void merge(Asset[] arr, int left, int mid, int right) {
        Asset[] temp = new Asset[right - left + 1];

        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            // Stable: <= keeps original order for equal returnRate
            if (arr[i].returnRate <= arr[j].returnRate) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    // 🔹 Quick Sort (Descending returnRate + Asc volatility)
    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {

            // Hybrid: use insertion sort for small partitions
            if (high - low < 10) {
                insertionSort(arr, low, high);
                return;
            }

            int pivotIndex = medianOfThree(arr, low, high);
            swap(arr, pivotIndex, high);

            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // 🔹 Partition (DESC returnRate, ASC volatility)
    public static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (
                    arr[j].returnRate > pivot.returnRate ||
                            (arr[j].returnRate == pivot.returnRate &&
                                    arr[j].volatility < pivot.volatility)
            ) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // 🔹 Median-of-3 Pivot Selection
    public static int medianOfThree(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;

        double a = arr[low].returnRate;
        double b = arr[mid].returnRate;
        double c = arr[high].returnRate;

        if ((a > b && a < c) || (a < b && a > c)) return low;
        if ((b > a && b < c) || (b < a && b > c)) return mid;
        return high;
    }

    // 🔹 Insertion Sort (for small partitions)
    public static void insertionSort(Asset[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Asset key = arr[i];
            int j = i - 1;

            while (j >= low && (
                    arr[j].returnRate < key.returnRate ||
                            (arr[j].returnRate == key.returnRate &&
                                    arr[j].volatility > key.volatility)
            )) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    // 🔹 Swap utility
    public static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {

        Asset[] assets = {
                new Asset("AAPL", 12, 5),
                new Asset("TSLA", 8, 7),
                new Asset("GOOG", 15, 4)
        };

        Asset[] mergeArr = Arrays.copyOf(assets, assets.length);
        Asset[] quickArr = Arrays.copyOf(assets, assets.length);

        // 🔹 Merge Sort (Ascending)
        mergeSort(mergeArr, 0, mergeArr.length - 1);
        System.out.println("Merge Sort (Ascending):");
        for (Asset a : mergeArr) System.out.print(a + " ");

        // 🔹 Quick Sort (Descending)
        quickSort(quickArr, 0, quickArr.length - 1);
        System.out.println("\n\nQuick Sort (Descending):");
        for (Asset a : quickArr) System.out.print(a + " ");
    }
}