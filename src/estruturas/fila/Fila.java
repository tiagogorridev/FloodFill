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

        // Cria um novo node
        QueueNode newNode = new QueueNode(item);

        if (rear == null) {
            
            // Se a fila ainda não tem nada, ela é o único elemento
            rear = newNode;
            front = rear;
            return; //finaliza a execução do método

        }

        // Cria mais um node na fila, sendo esse o último node
        rear.setNode(newNode); 
        rear = newNode;

    }

    public Object removeElementoDaFila() {
        
        if (isEmpty()) {

            // Se tiver vazia, não tem o que tirar
            throw new RuntimeException("Fila vazia");
        
        }

        Object item = front.getValor(); // pega o valor pra retornar depois, enquanto isso, tira o node da fila

        front = front.getNode(); // o front vira o próprio node

        if (front == null) {
            
            // se o front não tinha próximo, a fila fica vazia
            rear = null;

        }
        
        return item;

    }

    public Object pegaValorSemRemover() {

        if (isEmpty()) {

            // se tiver vazia, não tem o que exibir
            throw new RuntimeException("Fila vazia");

        }

        return front.getValor();

    }

    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer("Fila: ");

        QueueNode current = front;
        
        // Adiciona " <- " + "{proximo}" no sb
        while (current != null) {
        
            sb.append(current.getValor());
            
            if (current.getNode() != null) 
                sb.append(" <- ");

            current = current.getNode();
        
        }
        
        return sb.toString();
    
    }

}

