import java.util.Scanner;

/**
 * Rabbit Problem (Fibonacci's rabbits)
 * - Start with 1 newborn pair of rabbits.
 * - A pair becomes mature after 1 month, then produces 1 new pair every month.
 * - Rabbits never die.
 * Question: how many pairs after n months?
 *
 * Month: 1 2 3 4 5 6  7  8 ...
 * Pairs: 1 1 2 3 5 8 13 21 ...
 */
public class RabbitProblem {

    // Recursive version (simple, but slow for big n)
    public static long rabbitsRecursive(int month) {
        if (month <= 2) return 1;
        return rabbitsRecursive(month - 1) + rabbitsRecursive(month - 2);
    }

    // Iterative version (fast, O(n))
    public static long rabbitsIterative(int month) {
        if (month <= 2) return 1;
        long previous = 1;  // pairs last month
        long current = 1;   // pairs this month
        for (int m = 3; m <= month; m++) {
            long next = current + previous; // old pairs + new babies
            previous = current;
            current = next;
        }
        return current;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of months: ");
        int n = sc.nextInt();

        if (n < 1) {
            System.out.println("Months must be 1 or more.");
            return;
        }

        System.out.println("Month-by-month:");
        for (int m = 1; m <= n; m++) {
            System.out.println("Month " + m + " : " + rabbitsIterative(m) + " pair(s)");
        }

        System.out.println("\nRecursive result : " + rabbitsRecursive(Math.min(n, 40)));
        System.out.println("Iterative result : " + rabbitsIterative(n));
    }
}
