# DSA - Data Structures and Algorithms in Java

A Java-based collection of common data structures and algorithmic implementations created as part of a DSA coding session. This repository focuses on hands-on learning and practical implementations of core concepts such as stacks, queues, linked lists, and circular structures.

## Overview

This project contains clean, beginner-friendly Java implementations of essential data structures and variations built using fundamental operations. The goal is to strengthen understanding of:

- data structure behavior
- time and space complexity
- pointer-based and array-based design
- queue/stack variations
- implementation trade-offs between different approaches

## Repository Structure

```text
DSA/
├── README.md
├── arraycircularqueue.java
├── doublylinkedlist.java
├── singlylinkedlist.java
├── stack_queue.java
├── queuestack-pop_friendly.java
├── queuestack-push_friendly.java
├── stackqueue-dequeue_friendly.java
├── unbounded_array_stack.java
```

## Implementations Included

### 1) Array Circular Queue
File: `arraycircularqueue.java`

- Implements a circular queue using a fixed-size array
- Uses `front`, `rear`, and `size` to efficiently manage queue operations
- Supports:
  - `enqueue(T item)`
  - `dequeue()`
  - `peek()`
  - `isEmpty()`
  - `isFull()`
  - `size()`
- Time complexity:
  - enqueue: O(1)
  - dequeue: O(1)
  - peek: O(1)

This is useful when there is a fixed upper bound on storage and we want to reuse array slots efficiently.

### 2) Singly Linked List
File: `singlylinkedlist.java`

- Classic singly linked list with head and tail pointers
- Supports insertion and deletion at the front, end, and arbitrary positions
- Includes:
  - `addFirst()`
  - `addLast()`
  - `addAt()`
  - `removeFirst()`
  - `removeLast()`
  - `remove()`
  - `contains()`
  - `display()`
- Time complexity:
  - addFirst: O(1)
  - addLast: O(1)
  - addAt/remove by index: O(n)
  - search/contains: O(n)

This implementation demonstrates dynamic memory usage and node-based structure design.

### 3) Doubly Linked List
File: `doublylinkedlist.java`

- Node-based linked list with `prev` and `next` pointers
- Supports efficient insertion and deletion from both ends
- Includes:
  - `addFirst()`
  - `addLast()`
  - `addAt()`
  - `removeFirst()`
  - `removeLast()`
  - `remove()`
  - `contains()`
  - `displayForward()`
  - `displayBackward()`
- Time complexity:
  - addFirst: O(1)
  - addLast: O(1)
  - removeFirst: O(1)
  - removeLast: O(1)
  - search: O(n)

The main advantage over a singly linked list is that it supports easy traversal in both directions.

### 4) Unbounded Array Stack
File: `unbounded_array_stack.java`

- Stack implemented using a dynamic array
- Automatically resizes when the array becomes full
- Shrinks when usage drops significantly
- Includes:
  - `push()`
  - `pop()`
  - `peek()`
  - `isEmpty()`
  - `size()`
  - `getCapacity()`
- Time complexity:
  - push: amortized O(1)
  - pop: amortized O(1)
  - peek: O(1)

This demonstrates how a stack can grow dynamically without a fixed capacity.

### 5) Queue Using Two Stacks (Enqueue-Friendly)
File: `stack_queue.java`

- Implements a queue with two stacks
- Enqueue is O(1)
- Dequeue and peek are amortized O(1)
- Uses one stack for incoming elements and the other for outgoing elements

This is a classic interview-friendly data structure pattern showing how queue behavior can be simulated using stack operations.

### 6) Queue Stack (Pop-Friendly)
File: `queuestack-pop_friendly.java`

- Stack implemented using two queues
- `pop()` and `top()` are O(1)
- `push()` is O(n)
- Keeps the newest element at the front of the queue to make stack operations efficient

This version prioritizes fast pop/top access rather than fast insertion.

### 7) Queue Stack (Push-Friendly)
File: `queuestack-push_friendly.java`

- Stack implemented using two queues
- `push()` is O(1)
- `pop()` and `top()` are O(n)
- Rebalances queue ordering to maintain stack-like behavior

This version prioritizes quick insertion over quick pop/top operations.

### 8) Dequeue-Friendly Queue Using Two Stacks
File: `stackqueue-dequeue_friendly.java`

- Implements a queue where dequeue is O(1)
- Enqueue is O(n)
- Uses an auxiliary stack to maintain front-of-queue ordering

This demonstrates the tradeoff between making dequeue efficient versus keeping enqueue cheap.

## Programming Language

- Java

## Prerequisites

To run the programs, you need:

- Java JDK installed
- A terminal or IDE such as VS Code, IntelliJ IDEA, or Eclipse

## How to Run

Compile and run any Java file with:

```bash
javac filename.java
java ClassName
```

Example:

```bash
javac singlylinkedlist.java
java SinglyLinkedList
```

For classes whose file names are different from the class name, use the exact file name for compilation and the class name for execution.

## Example Workflow

```bash
javac arraycircularqueue.java
java ArrayCircularQueue
```

This will execute the example `main` method inside the file and print queue operations to the console.

## Learning Goals

This repository is intended to help learners understand:

- abstract data types (ADTs)
- object-oriented design in Java
- linked structures versus array-based structures
- complexity trade-offs in data structure design
- how common interview questions are modeled in code

## Why This Repository?

This project is a practical reference for DSA concepts. It is ideal for:

- students learning data structures
- interview preparation
- revising Java implementations of common DS problems
- quick experimentation with stack/queue/list behavior

## Future Improvements

Possible enhancements for this project include:

- adding more data structures such as trees, graphs, heaps, and hash tables
- adding unit tests for correctness
- documenting time complexity for each method
- adding visual examples of operation flow
- organizing files into folders by data structure category

## Contribution

This repository is currently a personal learning project. You can contribute by:

- improving code readability
- adding comments and documentation
- fixing edge cases
- adding more DSA implementations

## License

This project is intended for educational use and learning purposes.

## Author

Created as part of a Java DSA coding session focused on understanding core data structures through implementation.

---

If you want, I can also turn this into a more polished GitHub-style README with badges, a table of contents, and project screenshots or a custom logo section.