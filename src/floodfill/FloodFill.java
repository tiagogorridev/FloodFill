package floodfill;
import grid.Grid;
import coordenadas.Coordenada;
import pilha.Pilha;
import fila.Fila;

public class FloodFill {
    
    // Grid contendo a imagem a ser processada
    private Grid grid;
    
    // Construtor que recebe grid para operações de flood fill
    public FloodFill(Grid grid) {
        this.grid = grid;
    }
    
    // Flood Fill usando PILHA (abordagem depth-first)
    public void floodFillComPilha(Coordenada inicial, int novaCor) {
        // Validação inicial
        if (!grid.isCoordenadasValidas(inicial)) {
            System.out.println("Coordenada inválida: " + inicial);
            return;
        }
        
        int corOriginal = grid.getPixel(inicial);
        
        // Se a cor já é a mesma, não faz nada
        if (corOriginal == novaCor) {
            System.out.println("Cor já é a mesma, nada para pintar");
            return;
        }
        
        // Matriz de visitados para evitar processamento duplicado
        boolean[][] visitados = new boolean[grid.getLargura()][grid.getAltura()];
        
        Pilha pilha = new Pilha();
        pilha.push(inicial);
        visitados[inicial.getX()][inicial.getY()] = true;
        
        System.out.println("Flood Fill com PILHA:");
        System.out.println("- Coordenada inicial: " + inicial);
        System.out.println("- Cor original: " + corOriginal);
        System.out.println("- Nova cor: " + novaCor);
        
        int pixelsPintados = 0;
        long startTime = System.currentTimeMillis();
        
        while (!pilha.isEmpty()) {
            Coordenada atual = (Coordenada) pilha.pop();
            
            // Pinta o pixel
            grid.setPixel(atual, novaCor);
            pixelsPintados++;
            
            // Mostra progresso a cada 1000 pixels
            if (pixelsPintados % 1000 == 0) {
                System.out.println("Pixels pintados: " + pixelsPintados);
            }
            
            // Adiciona os vizinhos válidos na pilha
            for (Coordenada vizinho : atual.getVizinhos()) {
                if (grid.isCoordenadasValidas(vizinho) && 
                    !visitados[vizinho.getX()][vizinho.getY()] &&
                    grid.getPixel(vizinho) == corOriginal) {
                    
                    pilha.push(vizinho);
                    visitados[vizinho.getX()][vizinho.getY()] = true;
                }
            }
        }
        
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Total de pixels pintados com PILHA: " + pixelsPintados);
        System.out.println("Tempo de execução: " + duration + "ms");
    }
    
    // Flood Fill usando FILA (abordagem breadth-first)
    public void floodFillComFila(Coordenada inicial, int novaCor) {
        // Validação inicial
        if (!grid.isCoordenadasValidas(inicial)) {
            System.out.println("Coordenada inválida: " + inicial);
            return;
        }
        
        int corOriginal = grid.getPixel(inicial);
        
        // Se a cor já é a mesma, não faz nada
        if (corOriginal == novaCor) {
            System.out.println("Cor já é a mesma, nada para pintar");
            return;
        }
        
        // Matriz de visitados para evitar processamento duplicado
        boolean[][] visitados = new boolean[grid.getLargura()][grid.getAltura()];
        
        Fila fila = new Fila();
        fila.insereNaFila(inicial);
        visitados[inicial.getX()][inicial.getY()] = true;
        
        System.out.println("Flood Fill com FILA:");
        System.out.println("- Coordenada inicial: " + inicial);
        System.out.println("- Cor original: " + corOriginal);
        System.out.println("- Nova cor: " + novaCor);
        
        int pixelsPintados = 0;
        long startTime = System.currentTimeMillis();
        
        while (!fila.isEmpty()) {
            Coordenada atual = (Coordenada) fila.removeElementoDaFila();
            
            // Pinta o pixel
            grid.setPixel(atual, novaCor);
            pixelsPintados++;
            
            // Mostra progresso a cada 1000 pixels
            if (pixelsPintados % 1000 == 0) {
                System.out.println("Pixels pintados: " + pixelsPintados);
            }
            
            // Adiciona os vizinhos válidos na fila
            for (Coordenada vizinho : atual.getVizinhos()) {
                if (grid.isCoordenadasValidas(vizinho) && 
                    !visitados[vizinho.getX()][vizinho.getY()] &&
                    grid.getPixel(vizinho) == corOriginal) {
                    
                    fila.insereNaFila(vizinho);
                    visitados[vizinho.getX()][vizinho.getY()] = true;
                }
            }
        }
        
        long duration = System.currentTimeMillis() - startTime;
        System.out.println("Total de pixels pintados com FILA: " + pixelsPintados);
        System.out.println("Tempo de execução: " + duration + "ms");
    }
}