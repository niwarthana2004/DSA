public class CircularDoublyLinkedList<T> {

    private class Node {
        T data;
        Node next, prev;

        Node(T data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    // Add at the beginning
    public void addFirst(T data) {
        addLast(data);
        head = head.prev; // new node becomes head
    }

    // Add at the end
    public void addLast(T data) {
        Node node = new Node(data);
        if (head == null) {
            node.next = node;
            node.prev = node;
            head = node;
        } else {
            Node tail = head.prev;
            node.next = head;
            node.prev = tail;
            tail.next = node;
            head.prev = node;
        }
        size++;
    }

    // Add at a given index
    public void add(int index, T data) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index == 0) { addFirst(data); return; }
        if (index == size) { addLast(data); return; }

        Node cur = getNode(index);
        Node node = new Node(data);
        node.prev = cur.prev;
        node.next = cur;
        cur.prev.next = node;
        cur.prev = node;
        size++;
    }

    // Remove first
    public T removeFirst() {
        if (isEmpty()) throw new IllegalStateException("List is empty");
        return unlink(head);
    }

    // Remove last
    public T removeLast() {
        if (isEmpty()) throw new IllegalStateException("List is empty");
        return unlink(head.prev);
    }

    // Remove by index
    public T remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return unlink(getNode(index));
    }

    // Remove by value
    public boolean removeValue(T data) {
        if (isEmpty()) return false;
        Node cur = head;
        do {
            if (cur.data.equals(data)) {
                unlink(cur);
                return true;
            }
            cur = cur.next;
        } while (cur != head);
        return false;
    }

    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return getNode(index).data;
    }

    public boolean contains(T data) {
        if (isEmpty()) return false;
        Node cur = head;
        do {
            if (cur.data.equals(data)) return true;
            cur = cur.next;
        } while (cur != head);
        return false;
    }

    // Print forward
    public void displayForward() {
        if (isEmpty()) { System.out.println("[]"); return; }
        Node cur = head;
        StringBuilder sb = new StringBuilder("[");
        do {
            sb.append(cur.data);
            cur = cur.next;
            if (cur != head) sb.append(" <-> ");
        } while (cur != head);
        System.out.println(sb.append("]"));
    }

    // Print backward
    public void displayBackward() {
        if (isEmpty()) { System.out.println("[]"); return; }
        Node cur = head.prev;
        StringBuilder sb = new StringBuilder("[");
        do {
            sb.append(cur.data);
            cur = cur.prev;
            if (cur != head.prev) sb.append(" <-> ");
        } while (cur != head.prev);
        System.out.println(sb.append("]"));
    }

    // ---- helpers ----
    private Node getNode(int index) {
        Node cur;
        if (index < size / 2) {          // walk from head
            cur = head;
            for (int i = 0; i < index; i++) cur = cur.next;
        } else {                          // walk from tail
            cur = head.prev;
            for (int i = size - 1; i > index; i--) cur = cur.prev;
        }
        return cur;
    }

    private T unlink(Node node) {
        if (size == 1) {
            head = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            if (node == head) head = node.next;
        }
        size--;
        return node.data;
    }

    // ---- demo ----
    public static void main(String[] args) {
        CircularDoublyLinkedList<Integer> list = new CircularDoublyLinkedList<>();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addFirst(5);
        list.add(2, 15);

        list.displayForward();   // [5 <-> 10 <-> 15 <-> 20 <-> 30]
        list.displayBackward();  // [30 <-> 20 <-> 15 <-> 10 <-> 5]

        list.removeFirst();
        list.removeLast();
        list.removeValue(15);
        list.displayForward();   // [10 <-> 20]
    }
}
