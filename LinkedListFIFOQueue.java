public class LinkedListFIFOQueue<T> {

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node front; // remove from here
    private Node rear;  // add here
    private int size;

    // Add to the rear - O(1)
    public void enqueue(T data) {
        Node node = new Node(data);
        if (isEmpty()) {
            front = rear = node;
        } else {
            rear.next = node;
            rear = node;
        }
        size++;
    }

    // Remove and return the front - O(1)
    public T dequeue() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        T data = front.data;
        front = front.next;
        if (front == null) rear = null; // queue became empty
        size--;
        return data;
    }

    // Look at the front without removing - O(1)
    public T peek() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    public void clear() {
        front = rear = null;
        size = 0;
    }

    public void display() {
        if (isEmpty()) { System.out.println("[]"); return; }
        StringBuilder sb = new StringBuilder("[front] ");
        for (Node cur = front; cur != null; cur = cur.next) {
            sb.append(cur.data);
            if (cur.next != null) sb.append(" -> ");
        }
        sb.append(" [rear]");
        System.out.println(sb);
    }

    // ---- demo ----
    public static void main(String[] args) {
        LinkedListFIFOQueue<Integer> queue = new LinkedListFIFOQueue<>();
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.display();                    // [front] 10 -> 20 -> 30 [rear]

        System.out.println(queue.peek());    // 10
        System.out.println(queue.dequeue()); // 10
        queue.display();                    // [front] 20 -> 30 [rear]
        System.out.println(queue.size());    // 2
    }
}
