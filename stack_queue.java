import java.util.Stack;
import java.util.NoSuchElementException;

/**
 * StackQueue implementation where enqueue is O(1) (Enqueue-Friendly),
 * and dequeue/peek are amortized O(1) using two stacks.
 */
public class StackQueueEnqueueFriendly {

    private Stack<Integer> s1; // Used for incoming elements (enqueue)
    private Stack<Integer> s2; // Used for outgoing elements (dequeue)

    public StackQueueEnqueueFriendly() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /**
     * Enqueue operation: O(1) time complexity.
     * Simply pushes the new element onto the primary stack (s1).
     */
    public void enqueue(int data) {
        s1.push(data);
    }

    /**
     * Dequeue operation: Amortized O(1), Worst-case O(n).
     * If s2 is empty, transfers all elements from s1 to s2,
     * then pops and returns the top of s2.
     */
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }

        // Shift elements from s1 to s2 only when s2 is empty
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }

        return s2.pop();
    }

    /**
     * Peek operation: Amortized O(1), Worst-case O(n).
     * Returns the front element without removing it.
     */
    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty.");
        }

        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }

        return s2.peek();
    }

    /**
     * Checks if the queue is empty.
     */
    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    /**
     * Returns the total number of elements in the queue.
     */
    public int size() {
        return s1.size() + s2.size();
    }

    // Driver code for verification
    public static void main(String[] args) {
        StackQueueEnqueueFriendly queue = new StackQueueEnqueueFriendly();

        System.out.println("Enqueuing: 10, 20, 30");
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        System.out.println("Front element (peek): " + queue.peek()); // Expected: 10
        System.out.println("Dequeued: " + queue.dequeue());           // Expected: 10
        System.out.println("Front element after dequeue: " + queue.peek()); // Expected: 20

        System.out.println("Enqueuing: 40");
        queue.enqueue(40);

        System.out.println("\n--- Emptying Queue ---");
        while (!queue.isEmpty()) {
            System.out.println("Dequeued: " + queue.dequeue());
        }
    }
}
