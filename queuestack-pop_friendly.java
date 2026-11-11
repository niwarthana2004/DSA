import java.util.LinkedList;
import java.util.Queue;
import java.util.NoSuchElementException;

/**
 * QueueStack implementation where the pop/top operations are O(1) (Pop-Friendly),
 * and the push operation is O(n).
 */
public class QueueStackPopFriendly {

    private Queue<Integer> q1;
    private Queue<Integer> q2;

    public QueueStackPopFriendly() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    /**
     * Push operation: O(n) time complexity.
     * Enqueues the new item into q2, empties q1 into q2,
     * then swaps references so q1 always has the latest item at the front.
     */
    public void push(int data) {
        // Step 1: Add new element to empty helper queue q2
        q2.offer(data);

        // Step 2: Push all elements of q1 into q2
        while (!q1.isEmpty()) {
            q2.offer(q1.poll());
        }

        // Step 3: Swap q1 and q2 so q1 has all elements with newest in front
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    /**
     * Pop operation: O(1) time complexity.
     * Directly removes and returns the front element of q1.
     */
    public int pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        return q1.poll();
    }

    /**
     * Top/Peek operation: O(1) time complexity.
     * Directly returns the front element of q1 without removing it.
     */
    public int top() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }
        return q1.peek();
    }

    /**
     * Check if the stack is empty.
     */
    public boolean isEmpty() {
        return q1.isEmpty();
    }

    /**
     * Current size of the stack.
     */
    public int size() {
        return q1.size();
    }

    // Driver code for verification/testing
    public static void main(String[] args) {
        QueueStackPopFriendly stack = new QueueStackPopFriendly();

        System.out.println("Pushing elements: 10, 20, 30");
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Current Top (O(1)): " + stack.top()); // Expected: 30
        System.out.println("Popped (O(1)): " + stack.pop());       // Expected: 30
        System.out.println("Current Top (O(1)): " + stack.top()); // Expected: 20

        stack.push(40);
        System.out.println("Pushed 40, Current Top: " + stack.top()); // Expected: 40

        while (!stack.isEmpty()) {
            System.out.println("Popped: " + stack.pop());
        }
    }
}
