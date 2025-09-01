package pilha;

public class Pilha {

    private StackNode topo;
    private int tamanho;

    public Pilha(){

        this.tamanho = 0;
        this.topo = null;

    }

    public boolean isEmpty() {
        return topo == null;
    }

    public int size() {
        return tamanho;
    }

    public void push(Object valor) {
        StackNode novo = new StackNode(valor);
        novo.setNode(topo);
        topo = novo;
        tamanho++;
    }

    public Object pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Pilha vazia");
        }
        Object valor = topo.getValor();
        topo = topo.getNode();
        tamanho--;
        return valor;
    }

    public Object peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Pilha vazia");
        }
        return topo.getValor();
    }
    
}
