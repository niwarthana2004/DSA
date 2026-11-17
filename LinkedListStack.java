public class LinkedListStack<T> {

    private class Node {
        T data;
        Node next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node top;
    private int size;

    // Add to the top - O(1)
    public void push(T data) {
        Node node = new Node(data);
        node.next = top;
        top = node;
        size++;
    }

    // Remove and return the top - O(1)
    public T pop() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    // Look at the top without removing - O(1)
    public T peek() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        return top.data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    public void clear() {
        top = null;
        size = 0;
    }

    public void display() {
        if (isEmpty()) { System.out.println("[]"); return; }
        StringBuilder sb = new StringBuilder("[top] ");
        for (Node cur = top; cur != null; cur = cur.next) {
            sb.append(cur.data);
            if (cur.next != null) sb.append(" -> ");
        }
        System.out.println(sb);
    }

    // ---- demo ----
    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.display();                 // [top] 30 -> 20 -> 10

        System.out.println(stack.peek()); // 30
        System.out.println(stack.pop());  // 30
        stack.display();                 // [top] 20 -> 10
        System.out.println(stack.size()); // 2
    }
}
