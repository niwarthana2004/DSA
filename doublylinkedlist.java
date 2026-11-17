import java.util.NoSuchElementException;

public class DoublyLinkedList<T> {

    // Inner Node class with both next and prev pointers
    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public DoublyLinkedList() {
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
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    // Insert at the end: O(1)
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
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

        Node<T> current = getNodeAt(index);
        Node<T> newNode = new Node<>(data);

        newNode.prev = current.prev;
        newNode.next = current;
        current.prev.next = newNode;
        current.prev = newNode;
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
            tail = null;
        } else {
            head.prev = null; // Help garbage collection
        }
        return data;
    }

    // Remove from the end: O(1) — major advantage over SinglyLinkedList
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("List is empty");
        }
        T data = tail.data;
        tail = tail.prev;
        size--;

        if (isEmpty()) {
            head = null;
        } else {
            tail.next = null; // Help garbage collection
        }
        return data;
    }

    // Remove by value (first occurrence): O(n)
    public boolean remove(T key) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(key)) {
                if (current == head) {
                    removeFirst();
                } else if (current == tail) {
                    removeLast();
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                    size--;
                }
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Helper: traverses from head or tail depending on whichever is closer
    private Node<T> getNodeAt(int index) {
        Node<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

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

    // Print list forward: O(n)
    public void displayForward() {
        Node<T> current = head;
        System.out.print("null <- ");
        while (current != null) {
            System.out.print(current.data + (current.next != null ? " <-> " : " -> "));
            current = current.next;
        }
        System.out.println("null");
    }

    // Print list backward: O(n)
    public void displayBackward() {
        Node<T> current = tail;
        System.out.print("null <- ");
        while (current != null) {
            System.out.print(current.data + (current.prev != null ? " <-> " : " -> "));
            current = current.prev;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        list.addLast(10);
        list.addLast(20);
        list.addFirst(5);
        list.addAt(2, 15);

        list.displayForward();  // Output: null <- 5 <-> 10 <-> 15 <-> 20 -> null
        list.displayBackward(); // Output: null <- 20 <-> 15 <-> 10 <-> 5 -> null

        System.out.println("Removed first: " + list.removeFirst()); // Output: 5
        System.out.println("Removed last: " + list.removeLast());   // Output: 20
        list.remove(15);

        list.displayForward();  // Output: null <- 10 -> null
    }
}

