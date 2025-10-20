package Day2;

import java.util.NoSuchElementException;

public class CircularQueue {
    private int[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 8;
    private static final int MIN_CAPACITY = 4;

    /**
     * Constructor with default capacity
     */
    public CircularQueue() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructor with specified initial capacity
     * @param initialCapacity the initial capacity of the queue
     */
    public CircularQueue(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        this.capacity = initialCapacity;
        this.queue = new int[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    /**
     * Add an element to the rear of the queue
     * @param element the element to be added
     */
    public void enqueue(int element) {
        if (isFull()) {
            resize(2 * capacity); // Double the capacity when full
        }
        
        rear = (rear + 1) % capacity;
        queue[rear] = element;
        size++;
    }

    /**
     * Remove and return the element from the front of the queue
     * @return the front element
     * @throws NoSuchElementException if queue is empty
     */
    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot dequeue from empty queue");
        }
        
        int element = queue[front];
        front = (front + 1) % capacity;
        size--;
        
        // Shrink if size is 1/4 of capacity and capacity > minimum
        if (size > 0 && size <= capacity / 4 && capacity > MIN_CAPACITY) {
            resize(capacity / 2);
        }
        
        return element;
    }

    /**
     * Return the front element without removing it
     * @return the front element
     * @throws NoSuchElementException if queue is empty
     */
    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot peek from empty queue");
        }
        return queue[front];
    }

    /**
     * Check if the queue is empty
     * @return true if queue is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Check if the queue is full
     * @return true if queue is full, false otherwise
     */
    public boolean isFull() {
        return size == capacity;
    }

    /**
     * Get the number of elements in the queue
     * @return the size of the queue
     */
    public int size() {
        return size;
    }

    /**
     * Get the current capacity of the queue
     * @return the capacity
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Internal method to resize the queue
     * @param newCapacity the new capacity
     */
    private void resize(int newCapacity) {
        int[] newQueue = new int[newCapacity];
        
        // Copy elements to new array in correct order
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[(front + i) % capacity];
        }
        
        queue = newQueue;
        capacity = newCapacity;
        front = 0;
        rear = size - 1;
    }

    /**
     * Display the queue elements in order
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        
        System.out.print("Queue: ");
        for (int i = 0; i < size; i++) {
            System.out.print(queue[(front + i) % capacity] + " ");
        }
        System.out.println();
    }

    /**
     * Display detailed information about the queue
     */
    public void displayDetails() {
        System.out.println("=== Queue Details ===");
        System.out.println("Size: " + size);
        System.out.println("Capacity: " + capacity);
        System.out.println("Front: " + front);
        System.out.println("Rear: " + rear);
        display();
        System.out.println("=====================");
    }

    /**
     * Clear all elements from the queue
     */
    public void clear() {
        front = 0;
        rear = -1;
        size = 0;
        // Optional: Reset to default capacity
        if (capacity != DEFAULT_CAPACITY) {
            capacity = DEFAULT_CAPACITY;
            queue = new int[capacity];
        }
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "CircularQueue[]";
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("CircularQueue[");
        for (int i = 0; i < size; i++) {
            sb.append(queue[(front + i) % capacity]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    // Test method
    public static void main(String[] args) {
        System.out.println("=== Testing Dynamic Circular Queue ===\n");
        
        // Create a queue with initial capacity 4
        CircularQueue cq = new CircularQueue(4);
        System.out.println("Initial queue created with capacity 4");
        cq.displayDetails();
        
        // Test enqueue operations
        System.out.println("\n--- Enqueue Operations ---");
        for (int i = 1; i <= 10; i++) {
            cq.enqueue(i);
            System.out.println("After enqueue(" + i + "):");
            cq.displayDetails();
        }
        
        // Test dequeue operations
        System.out.println("\n--- Dequeue Operations ---");
        for (int i = 0; i < 5; i++) {
            int element = cq.dequeue();
            System.out.println("Dequeued: " + element);
            cq.displayDetails();
        }
        
        // Test circular behavior with more enqueues
        System.out.println("\n--- Testing Circular Behavior ---");
        for (int i = 11; i <= 15; i++) {
            cq.enqueue(i);
            System.out.println("After enqueue(" + i + "):");
            cq.displayDetails();
        }
        
        // Test peek
        System.out.println("\n--- Peek Operation ---");
        System.out.println("Front element: " + cq.peek());
        
        // Test edge cases
        System.out.println("\n--- Edge Cases ---");
        CircularQueue emptyQueue = new CircularQueue();
        System.out.println("Empty queue is empty: " + emptyQueue.isEmpty());
        
        try {
            emptyQueue.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
        
        try {
            emptyQueue.peek();
        } catch (NoSuchElementException e) {
            System.out.println("Expected exception: " + e.getMessage());
        }
        
        // Test clear operation
        System.out.println("\n--- Clear Operation ---");
        cq.clear();
        System.out.println("After clear:");
        cq.displayDetails();
        
        // Test toString
        cq.enqueue(100);
        cq.enqueue(200);
        cq.enqueue(300);
        System.out.println("\n--- toString Test ---");
        System.out.println(cq);
    }
}