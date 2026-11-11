import java.util.LinkedList;
import java.util.Queue;
import java.util.NoSuchElementException;

/**
 * QueueStack implementation where the push operation is O(1) (Push-Friendly),
 * and the pop/peek operations are O(n).
 */
public class QueueStackPushFriendly {

    private Queue<Integer> q1;
    private Queue<Integer> q2;

    public QueueStackPushFriendly() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    /**
     * Push operation: O(1) time complexity.
     * Simply enqueues the element into the primary queue (q1).
     */
    public void push(int data) {
        q1.offer(data);
    }

    /**
     * Pop operation: O(n) time complexity.
     * Moves (size - 1) elements to q2, dequeues and returns the last element,
     * then swaps the references of q1 and q2.
     */
    public int pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }

        // Leave only the last inserted element in q1
        while (q1.size() > 1) {
            q2.offer(q1.poll());
        }

        // The last element is the top of the stack
        int poppedElement = q1.poll();

        // Swap q1 and q2
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;

        return poppedElement;
    }

    /**
     * Top/Peek operation: O(n) time complexity.
     * Returns the element on top of the stack without removing it.
     */
    public int top() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty.");
        }

        // Transfer elements to q2 while keeping track of the last element
        while (q1.size() > 1) {
            q2.offer(q1.poll());
        }

        int topElement = q1.poll();
        q2.offer(topElement); // re-insert it back into q2

        // Swap q1 and q2
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;

        return topElement;
    }

    /**
     * Check if the stack is empty.
     */
    public boolean isEmpty() {
        return q1.isEmpty();
    }

    /**
     * Get the current size of the stack.
     */
    public int size() {
        return q1.size();
    }

    // Driver code for verification/testing
    public static void main(String[] args) {
        QueueStackPushFriendly stack = new QueueStackPushFriendly();

        System.out.println("Pushing elements: 10, 20, 30");
        stack.push(10);
        stack.push(20);
        stack.push(30);

        System.out.println("Current Top: " + stack.top()); // Should output 30
        System.out.println("Popped: " + stack.pop());       // Should output 30
        System.out.println("Current Top: " + stack.top()); // Should output 20

        stack.push(40);
        System.out.println("Pushed 40, Current Top: " + stack.top()); // Should output 40

        while (!stack.isEmpty()) {
            System.out.println("Popped: " + stack.pop());
        }
    }
}
