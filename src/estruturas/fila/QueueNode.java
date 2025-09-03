package fila;

public class QueueNode {

    private int valor;
    private QueueNode node;

    public QueueNode(int valor) {
        this.valor = valor;
        this.node = null;
    }

    public QueueNode(int valor, QueueNode node) {
        this.valor = valor;
        this.node = node;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public QueueNode getNode() {
        return node;
    }

    public void setNode(QueueNode node) {
        this.node = node;
    }
    
}
