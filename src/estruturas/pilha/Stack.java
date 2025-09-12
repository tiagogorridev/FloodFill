package pilha;

public class Stack<T> {
    private final Object[] elements;
    private final int capacity;
    private int top;

    public Stack(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacidade precisa ser maior do que zero");

        this.elements = new Object[capacity];
        this.capacity = capacity;
        this.top = -1;
    }

    public void push(T element) {
        if (top == capacity - 1) throw new IllegalStateException("STACK OVERFLOW!!!! =)");

        elements[++top] = element;
    }

    public T pop() {
        if (top == -1) throw new IllegalStateException("Stack está vazia");

        var element = (T)elements[top];
        elements[top--] = null;
        return element;
    }

    public boolean isEmpty() {
        return top == -1;
    }

}
