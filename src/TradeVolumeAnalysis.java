import java.util.*;

class Trade {
    String id;
    int volume;

    public Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id + ":" + volume;
    }
}

public class TradeVolumeAnalysis {

    // 🔹 Merge Sort (Ascending - Stable)
    public static void mergeSort(Trade[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            merge(arr, left, mid, right);
        }
    }

    public static void merge(Trade[] arr, int left, int mid, int right) {
        Trade[] temp = new Trade[right - left + 1];

        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i].volume <= arr[j].volume) { // stable condition
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        // copy back
        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    // 🔹 Quick Sort (Descending)
    public static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Lomuto Partition (DESC)
    public static int partition(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].volume > pivot) { // DESC
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // 🔹 Merge Two Sorted Arrays
    public static Trade[] mergeTwoSorted(Trade[] a, Trade[] b) {
        Trade[] result = new Trade[a.length + b.length];

        int i = 0, j = 0, k = 0;

        while (i < a.length && j < b.length) {
            if (a[i].volume <= b[j].volume) {
                result[k++] = a[i++];
            } else {
                result[k++] = b[j++];
            }
        }

        while (i < a.length) result[k++] = a[i++];
        while (j < b.length) result[k++] = b[j++];

        return result;
    }

    // 🔹 Total Volume
    public static int totalVolume(Trade[] arr) {
        int sum = 0;
        for (Trade t : arr) {
            sum += t.volume;
        }
        return sum;
    }

    public static void main(String[] args) {

        Trade[] trades = {
                new Trade("trade3", 500),
                new Trade("trade1", 100),
                new Trade("trade2", 300)
        };

        // Copy arrays
        Trade[] mergeArr = Arrays.copyOf(trades, trades.length);
        Trade[] quickArr = Arrays.copyOf(trades, trades.length);

        // 🔹 Merge Sort (Ascending)
        mergeSort(mergeArr, 0, mergeArr.length - 1);
        System.out.println("Merge Sort (Ascending):");
        for (Trade t : mergeArr) System.out.print(t + " ");

        // 🔹 Quick Sort (Descending)
        quickSort(quickArr, 0, quickArr.length - 1);
        System.out.println("\n\nQuick Sort (Descending):");
        for (Trade t : quickArr) System.out.print(t + " ");

        // 🔹 Merge two sorted lists (example)
        Trade[] morning = {
                new Trade("m1", 100),
                new Trade("m2", 300)
        };

        Trade[] afternoon = {
                new Trade("a1", 200),
                new Trade("a2", 400)
        };

        Trade[] merged = mergeTwoSorted(morning, afternoon);

        System.out.println("\n\nMerged Trades:");
        for (Trade t : merged) System.out.print(t + " ");

        // 🔹 Total volume
        System.out.println("\n\nTotal Volume: " + totalVolume(merged));
    }
}