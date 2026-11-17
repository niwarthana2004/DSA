import java.util.NoSuchElementException;

public class ArrayCircularQueue<T> {
    private T[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public ArrayCircularQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be greater than 0");
        }
        this.capacity = capacity;
        this.queue = (T[]) new Object[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    // Enqueue: O(1) time complexity
    public void enqueue(T item) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        // Wrap rear around to index 0 if it reaches the end
        rear = (rear + 1) % capacity;
        queue[rear] = item;
        size++;
    }

    // Dequeue: O(1) time complexity
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        T item = queue[front];
        queue[front] = null; // Prevent memory leak (allow garbage collection)
        // Wrap front around to index 0 if it reaches the end
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    // Peek: O(1) time complexity
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return queue[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        ArrayCircularQueue<Integer> cq = new ArrayCircularQueue<>(3);

        cq.enqueue(10);
        cq.enqueue(20);
        cq.enqueue(30);
        System.out.println("Is full? " + cq.isFull());        // Output: true

        System.out.println("Dequeued: " + cq.dequeue());      // Output: 10
        System.out.println("Dequeued: " + cq.dequeue());      // Output: 20

        // Wraps around to indices 0 and 1
        cq.enqueue(40);
        cq.enqueue(50);

        System.out.println("Front element: " + cq.peek());    // Output: 30
        System.out.println("Dequeued: " + cq.dequeue());      // Output: 30
        System.out.println("Dequeued: " + cq.dequeue());      // Output: 40
        System.out.println("Dequeued: " + cq.dequeue());      // Output: 50
    }
}
