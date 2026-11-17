import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {

    // Inner Node class
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Insert at the beginning: O(1)
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    // Insert at the end: O(1) using tail pointer
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // Insert at a specific index: O(n)
    public void addAt(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            addLast(data);
            return;
        }

        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        Node<T> newNode = new Node<>(data);
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    // Remove from the beginning: O(1)
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        T data = head.data;
        head = head.next;
        size--;
        if (isEmpty()) {
            tail = null; // Clean up tail if list became empty
        }
        return data;
    }

    // Remove from the end: O(n) because we must find the node before tail
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        if (size == 1) {
            return removeFirst();
        }

        Node<T> current = head;
        while (current.next != tail) {
            current = current.next;
        }
        T data = tail.data;
        tail = current;
        tail.next = null;
        size--;
        return data;
    }

    // Remove by value (first occurrence): O(n)
    public boolean remove(T key) {
        if (isEmpty()) return false;

        if (head.data.equals(key)) {
            removeFirst();
            return true;
        }

        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(key)) {
            current = current.next;
        }

        if (current.next == null) {
            return false; // Element not found
        }

        if (current.next == tail) {
            tail = current; // Update tail if the last node was removed
        }
        current.next = current.next.next;
        size--;
        return true;
    }

    // Search for an element: O(n)
    public boolean contains(T key) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(key)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    // Print list elements
    public void display() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        list.addLast(10);
        list.addLast(20);
        list.addFirst(5);
        list.addAt(2, 15);

        list.display(); // Output: 5 -> 10 -> 15 -> 20 -> null

        System.out.println("Removed first: " + list.removeFirst()); // Output: 5
        System.out.println("Removed last: " + list.removeLast());   // Output: 20
        list.remove(15);

        list.display(); // Output: 10 -> null
        System.out.println("Contains 10? " + list.contains(10));    // Output: true
    }
}
