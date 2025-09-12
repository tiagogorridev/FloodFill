package fila;
public class Fila {

    private QueueNode front;
    private QueueNode rear;

    public Fila() {
        front = null;
        rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void insereNaFila(Object item) {
        QueueNode newNode = new QueueNode(item);

        if (rear == null) { 
            rear = newNode;   // o novo nó é o rear
            front = rear;     // e também é o front
            return;
        }

        rear.setNode(newNode); // Conecta o nó atual ao próximo
        rear = newNode;        // Atualiza rear para o novo nó
    }

    public Object removeElementoDaFila() {
        if (isEmpty()) {
            throw new RuntimeException("Fila vazia");
        }

        Object item = front.getValor();    // Guarda o valor do front
        front = front.getNode();           // Avança o front para o próximo nó

        if (front == null) {
            rear = null; // Se a fila ficou vazia, rear também é null
        }

        return item;
    }

    public Object pegaValorSemRemover() {
        if (isEmpty()) {
            throw new RuntimeException("Fila vazia");
        }
        return front.getValor();
    }

    // Imprimir a fila
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("Fila: ");
        QueueNode current = front;

        while (current != null) {
            sb.append(current.getValor());

            if (current.getNode() != null) 
                sb.append(" <- "); // seta visual entre os nós

            current = current.getNode();
        }

        return sb.toString();
    }

}

