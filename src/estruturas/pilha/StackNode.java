package pilha;

public class StackNode {

    private Object valor;
    private StackNode node;

    public StackNode(StackNode node, Object valor){
        this.valor = valor;
        this.node = node;
    }

    public StackNode(Object valor){
        this.valor = valor;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    public StackNode getNode() {
        return node;
    }

    public void setNode(StackNode node) {
        this.node = node;
    }
    
    @Override
    public String toString() {
        return "Node{valor=" + valor + ", node=" + node + "}";
    }

}