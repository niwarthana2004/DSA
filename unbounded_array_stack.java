import java.util.EmptyStackException;

/**
 * Unbounded Array Stack implementation.
 * Dynamically expands when full and shrinks when significantly empty.
 */
public class UnboundedArrayStack {

    private int[] array;
    private int capacity;
    private int topIndex; // Points to the next available slot (also represents current size)

    private static final int INITIAL_CAPACITY = 2; // Starting capacity

    public UnboundedArrayStack() {
        this.capacity = INITIAL_CAPACITY;
        this.array = new int[capacity];
        this.topIndex = 0;
    }

    /**
     * Push operation: Adds an item to the top of the stack.
     * Automatically doubles capacity when full.
     */
    public void push(int data) {
        if (topIndex == capacity) {
            resize(2 * capacity); // Double capacity
        }
        array[topIndex++] = data;
    }

    /**
     * Pop operation: Removes and returns the top item.
     * Automatically halves capacity when usage drops to 1/4 of total capacity
     * (ensuring capacity does not drop below INITIAL_CAPACITY).
     */
    public int pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        int poppedValue = array[--topIndex];

        // Shrink array if size drops to 1/4 of capacity
        if (topIndex > 0 && topIndex == capacity / 4 && capacity / 2 >= INITIAL_CAPACITY) {
            resize(capacity / 2); // Halve capacity
        }

        return poppedValue;
    }

    /**
     * Peek/Top operation: Returns the top item without removing it.
     */
    public int peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return array[topIndex - 1];
    }

    /**
     * Helper method to resize the internal storage array.
     */
    private void resize(int newCapacity) {
        int[] newArray = new int[newCapacity];
        for (int i = 0; i < topIndex; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
        capacity = newCapacity;
        System.out.println("-> Resized array capacity to: " + capacity);
    }

    /**
     * Returns true if stack is empty.
     */
    public boolean isEmpty() {
        return topIndex == 0;
    }

    /**
     * Returns the current number of elements in the stack.
     */
    public int size() {
        return topIndex;
    }

    /**
     * Returns the current internal array capacity (useful for testing resizing).
     */
    public int getCapacity() {
        return capacity;
    }

    // Driver code for verification
    public static void main(String[] args) {
        UnboundedArrayStack stack = new UnboundedArrayStack();

        System.out.println("Initial capacity: " + stack.getCapacity());

        System.out.println("\n--- Pushing Elements ---");
        for (int i = 1; i <= 9; i++) {
            stack.push(i * 10);
            System.out.println("Pushed: " + (i * 10) + " | Size: " + stack.size() + " | Capacity: " + stack.getCapacity());
        }

        System.out.println("\nPeek top element: " + stack.peek());

        System.out.println("\n--- Popping Elements (Demonstrating Shrinkage) ---");
        while (!stack.isEmpty()) {
            int val = stack.pop();
            System.out.println("Popped: " + val + " | Size: " + stack.size() + " | Capacity: " + stack.getCapacity());
        }
    }
}
