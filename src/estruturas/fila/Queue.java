package fila;
public class Queue<T> {
    private final T[] queue;
    private int front = 0;
    private int rear = 0;
    private int size = 0;
    private final int capacity;

    public Queue(int queueSize) {
        if (queueSize <= 0) throw new IllegalStateException("Capacidade de fila precisa ser maior que 0");
        this.capacity = queueSize;
        this.queue = (T[]) new Object[queueSize];
    }

    public void push(T newItem) {
        if (size == capacity) throw new IndexOutOfBoundsException("Fila está cheia");

        queue[rear] = newItem; // Coloca elemento na posição rear
        rear = (rear + 1) % capacity; // Move rear para próxima posição (circular)
        size++;
    }

    public T pop() {
        if (size == 0) throw new NegativeArraySizeException("Fila está vazia");

        T temp = queue[front]; // Guarda o elemento do início da fila
        queue[front] = null;
        front = (front + 1) % capacity;  // fila circular
        size--;

        return temp;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}