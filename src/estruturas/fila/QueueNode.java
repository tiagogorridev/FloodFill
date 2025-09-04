package fila;

public class QueueNode {

    private Object valor;
    private QueueNode node;

    public QueueNode(Object valor) {
        this.valor = valor;
        this.node = null;
    }

    public QueueNode(Object valor, QueueNode node) {
        this.valor = valor;
        this.node = node;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public QueueNode getNode() {
        return node;
    }

    public void setNode(QueueNode node) {
        this.node = node;
    }
    
}
