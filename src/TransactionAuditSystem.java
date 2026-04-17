import java.util.*;
import java.time.*;

class Transaction {
    String id;
    double fee;
    LocalTime timestamp;

    public Transaction(String id, double fee, String ts) {
        this.id = id;
        this.fee = fee;
        this.timestamp = LocalTime.parse(ts);
    }

    @Override
    public String toString() {
        return id + ":" + fee + "@" + timestamp;
    }
}

public class TransactionAuditSystem {

    // 🔹 Bubble Sort (by fee only)
    public static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        int passes = 0, swaps = 0;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            passes++;

            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).fee > list.get(j + 1).fee) {
                    Collections.swap(list, j, j + 1);
                    swaps++;
                    swapped = true;
                }
            }

            // Early termination (optimized)
            if (!swapped) break;
        }

        System.out.println("Bubble Sort Result:");
        list.forEach(t -> System.out.print(t + " "));
        System.out.println("\nPasses: " + passes + ", Swaps: " + swaps);
    }

    // 🔹 Insertion Sort (by fee + timestamp)
    public static void insertionSort(List<Transaction> list) {
        int shifts = 0;

        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            // Compare fee first, then timestamp
            while (j >= 0 && (
                    list.get(j).fee > key.fee ||
                            (list.get(j).fee == key.fee &&
                                    list.get(j).timestamp.isAfter(key.timestamp))
            )) {
                list.set(j + 1, list.get(j));
                shifts++;
                j--;
            }

            list.set(j + 1, key);
        }

        System.out.println("\nInsertion Sort Result:");
        list.forEach(t -> System.out.print(t + " "));
        System.out.println("\nShifts: " + shifts);
    }

    // 🔹 High-fee Outlier Detection
    public static void findOutliers(List<Transaction> list) {
        System.out.println("\nHigh-fee Outliers (>50):");

        boolean found = false;
        for (Transaction t : list) {
            if (t.fee > 50) {
                System.out.println(t);
                found = true;
            }
        }

        if (!found) {
            System.out.println("None");
        }
    }

    public static void main(String[] args) {

        List<Transaction> transactions = new ArrayList<>();

        // Sample Input
        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        int size = transactions.size();

        // 🔹 Choose sorting strategy
        if (size <= 100) {
            bubbleSort(transactions);
        } else if (size <= 1000) {
            insertionSort(transactions);
        } else {
            System.out.println("Large dataset - use advanced sort (not covered)");
        }

        // 🔹 Outlier detection
        findOutliers(transactions);
    }
}