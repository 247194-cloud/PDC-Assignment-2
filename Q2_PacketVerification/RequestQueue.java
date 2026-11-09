public class RequestQueue {
    private char[] queue;
    private int front;
    private int rear;
    private int size;

    public RequestQueue(int capacity) {
        queue = new char[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(char c) {
        if (size == queue.length) {
            throw new IllegalStateException("Queue is full");
        }

        rear = (rear + 1) % queue.length;
        queue[rear] = c;
        size++;
    }

    public char dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        char value = queue[front];
        front = (front + 1) % queue.length;
        size--;
        return value;
    }

    public char peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        return queue[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }
}

