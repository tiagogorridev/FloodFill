package list;

public class MyArrayList<T> {
    
    private Object[] meuArray;
    private int tamanho;
    private int capacidade;

    public MyArrayList() {
        this(10);
    }

    public MyArrayList(int capacity) {
        this.capacidade = capacity;
        this.meuArray = new Object[capacity];
        this.tamanho = 0;
    }

    public void add(T e) {
        validaCapacidade(tamanho + 1);
        meuArray[tamanho++] = e;
    }

    public void add(int index, T e) {
        confereIndiceParaAdicionar(index);
        validaCapacidade(tamanho + 1);
    
        // Move todos os elementos a partir do índice para o final, abrindo espaço para o novo elemento
        // Começa do final (tamanho) e vai até o índice, deslocando cada elemento uma posição à frente
        for (int i = tamanho; i > index; i--) {
            meuArray[i] = meuArray[i - 1];
        }
    
        meuArray[index] = e; // Insere o novo elemento na posição desejada
        tamanho++;
    }

    public void remove(int index) {
        confereIndice(index);
    
        int numMoved = tamanho - index - 1; // Calcula quantos elementos precisam ser movidos
    
        // Se houver elementos após o índice, move-os para preencher o espaço
        if (numMoved > 0) {
            // Copia os elementos do array, começando do índice + 1 para o índice atual
            for (int i = index; i < tamanho - 1; i++) {
                meuArray[i] = meuArray[i + 1];
            }
        }
    
        tamanho -= 1; // Remove a referência ao último elemento e diminui o tamanho
        meuArray[tamanho] = null;
    }

    public void remove(Object o) {
        int idx = indexOf(o);

        if (idx >= 0) {
            remove(idx);
        }
    }

    // Substituir o elemento
    public void set(int index, T e) {
        confereIndice(index);
        meuArray[index] = e;
    }

    // Acessar o elemento
    @SuppressWarnings("unchecked")
    public T get(int index) {
        confereIndice(index);
        return (T) meuArray[index];
    }

    public boolean contains(Object objeto) {
        if (objeto == null) {
            for (int i = 0; i < tamanho; i++){
                if (meuArray[i] == null) return true;
            }
        } else {
            for (int i = 0; i < tamanho; i++){
                if (objeto.equals(meuArray[i])) return true;
            }
        }

        return false;
    }

    public int indexOf(Object objeto) {
        if (objeto == null) {
            for (int i = 0; i < tamanho; i++){
                if (meuArray[i] == null) return i;
            }
        } else {
            for (int i = 0; i < tamanho; i++){
                if (objeto.equals(meuArray[i])) return i;
            }
        }

        return -1;
    }

    public Object[] getArray() {
        Object[] novoArray = new Object[tamanho];
        
        for (int i = 0; i < novoArray.length; i++) {
            novoArray[i] = meuArray[i];
        }
        
        return novoArray;
    }

    public int size() {
        return tamanho;
    }

    private void validaCapacidade(int capacidadeMinima) {
        if (capacidadeMinima > capacidade) {
            int novaCapacidade = Math.max(capacidade * 2, capacidadeMinima);
            Object[] novoArray = new Object[novaCapacidade];
            System.arraycopy(meuArray, 0, novoArray, 0, tamanho);
        
            meuArray = novoArray;
            capacidade = novaCapacidade;
        
        }
    }

    private void confereIndice(int index) {
        if (index < 0 || index >= tamanho)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + tamanho);
    }

    private void confereIndiceParaAdicionar(int indice) {
        if (indice < 0 || indice > tamanho)
            throw new IndexOutOfBoundsException("Index: " + indice + ", Size: " + tamanho);
    }
}
