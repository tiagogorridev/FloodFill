package floodfill;
import grid.Grid;
import coordenadas.Coordenada;
import exception.PintarImagemException;
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
        
        System.out.println("Iniciando pintura com Pilha");

        int corOriginal;

        try {

            corOriginal = validaInicioImagem(inicial, novaCor);
            
        } catch (PintarImagemException e) {

            System.out.println(e.getMessage());
            
            return;

        }

        // Matriz de visitados para evitar processamento duplicado
        boolean[][] visitados = new boolean[grid.getLargura()][grid.getAltura()];
        
        Pilha pilha = new Pilha();

        // O algoritmo começa na coordenada inicial, e vai "explorando" a imagem
        pilha.push(inicial);
        visitados[inicial.getX()][inicial.getY()] = true;

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

                if (
                    grid.isCoordenadasValidas(vizinho) && 
                    !visitados[vizinho.getX()][vizinho.getY()] &&
                    grid.getPixel(vizinho) == corOriginal
                ) {

                    pilha.push(vizinho);
                    
                    visitados[vizinho.getX()][vizinho.getY()] = true;

                }

            }

        }
        
        System.out.println("Total de pixels pintados com PILHA: " + pixelsPintados);
        System.out.println("Tempo de execução: " +  (System.currentTimeMillis() - startTime) + "ms");
        
    }
    
    // Flood Fill usando FILA (abordagem breadth-first)
    public void floodFillComFila(Coordenada inicial, int novaCor) {
        
        Integer corOriginal= validaInicioImagem(inicial, novaCor);

        if(corOriginal == null) return;

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

                if (
                    grid.isCoordenadasValidas(vizinho) && 
                    !visitados[vizinho.getX()][vizinho.getY()] &&
                    grid.getPixel(vizinho) == corOriginal
                ) {
                    
                    fila.insereNaFila(vizinho);
                    visitados[vizinho.getX()][vizinho.getY()] = true;

                }

            }

        }
        
        System.out.println("Total de pixels pintados com FILA: " + pixelsPintados);
        System.out.println("Tempo de execução: " + (System.currentTimeMillis() - startTime) + "ms");

    }

    private Integer validaInicioImagem(Coordenada coordenadas, int novaCor) {
        
        // valida coordenadas iniciais
        if (!grid.isCoordenadasValidas(coordenadas)) {

            System.out.println("Coordenada inválida: " + coordenadas);
            return null;

        }

        // Se a cor já é a mesma, não precisa pintar
        int corOriginal = grid.getPixel(coordenadas);

        if (corOriginal == novaCor){

            System.out.println("Cor já é a mesma, nada para pintar");
            return null;

        }

        return corOriginal;
        
    }

}