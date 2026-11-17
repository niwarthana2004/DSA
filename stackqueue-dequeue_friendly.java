import java.util.Stack;
import java.util.NoSuchElementException;

public class DequeueFriendlyQueue<T> {
    private Stack<T> s1; // Primary stack (front of queue is at the top)
    private Stack<T> s2; // Auxiliary stack for shifting elements

    public DequeueFriendlyQueue() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    // Enqueue: O(n) time complexity
    public void enqueue(T item) {
        // 1. Move all elements from s1 to s2
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }

        // 2. Push the new item onto s1
        s1.push(item);

        // 3. Move everything back from s2 to s1
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }

    // Dequeue: O(1) time complexity
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return s1.pop();
    }

    // Peek: O(1) time complexity
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return s1.peek();
    }

    public boolean isEmpty() {
        return s1.isEmpty();
    }

    public int size() {
        return s1.size();
    }

    public static void main(String[] args) {
        DequeueFriendlyQueue<Integer> queue = new DequeueFriendlyQueue<>();
        
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        System.out.println("Front element: " + queue.peek());    // Output: 10
        System.out.println("Dequeued: " + queue.dequeue());      // Output: 10
        System.out.println("Dequeued: " + queue.dequeue());      // Output: 20
        
        queue.enqueue(40);
        System.out.println("Dequeued: " + queue.dequeue());      // Output: 30
        System.out.println("Dequeued: " + queue.dequeue());      // Output: 40
    }
}
