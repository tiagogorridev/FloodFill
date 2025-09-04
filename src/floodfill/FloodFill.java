package floodfill;
import estruturas.Queue;
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

        //System.out.println("Flood Fill com FILA:");
        //System.out.println("- Coordenada inicial: " + inicial);
        //System.out.println("- Cor original: " + corOriginal);
        //System.out.println("- Nova cor: " + novaCor);

        int pixelsPintados = 0;
        long startTime = System.currentTimeMillis();

        while (!fila.isEmpty()) {
            Coordenada atual = (Coordenada) fila.removeElementoDaFila();

            // Pinta o pixel
            grid.setPixel(atual, novaCor);
            //pixelsPintados++;

            // Mostra progresso a cada 1000 pixels
            /*if (pixelsPintados % 1000 == 0) {
                System.out.println("Pixels pintados: " + pixelsPintados);
            }*/

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
        //System.out.println("Total de pixels pintados com FILA: " + pixelsPintados);
        System.out.println("Tempo de execução: " + duration + "ms");
    }

    public void floodFillComQueue(Coordenada beginning, int newColor) {
        try {
            if (!grid.isCoordenadasValidas(beginning)) {
                throw new Exception("Coordenada inicial inválida: " + beginning);
            }

            int originalColor = grid.getPixel(beginning);
            if (originalColor == newColor) {
                throw new Exception("Cor original igual a cor destino");
            }

            final int width = grid.getLargura();
            final int height = grid.getAltura();
            final int totalPixels = width * height;

            boolean[] visited = new boolean[totalPixels];
            Queue<Integer> coordsToVisit = new Queue<>(totalPixels);

            int beginningIdx = beginning.getY() * width + beginning.getX();
            coordsToVisit.push(beginningIdx);
            visited[beginningIdx] = true;
            grid.setPixel(beginning, newColor);

            long startTime = System.currentTimeMillis();

            while (!coordsToVisit.isEmpty()) {
                int currentIdx = coordsToVisit.pop();
                int currentX = currentIdx % width;
                int currentY = currentIdx / width;

                if (currentX > 0) {
                    int leftIdx = currentIdx - 1;
                    if (!visited[leftIdx]) {
                        Coordenada leftCoord = new Coordenada(currentX - 1, currentY);
                        if (grid.getPixel(leftCoord) == originalColor) {
                            grid.setPixel(leftCoord, newColor);
                            visited[leftIdx] = true;
                            coordsToVisit.push(leftIdx);
                        }
                    }
                }

                if (currentX < width - 1) {
                    int rightIdx = currentIdx + 1;
                    if (!visited[rightIdx]) {
                        Coordenada rightCoord = new Coordenada(currentX + 1, currentY);
                        if (grid.getPixel(rightCoord) == originalColor) {
                            grid.setPixel(rightCoord, newColor);
                            visited[rightIdx] = true;
                            coordsToVisit.push(rightIdx);
                        }
                    }
                }

                if (currentY > 0) {
                    int upIdx = currentIdx - width;
                    if (!visited[upIdx]) {
                        Coordenada upCoord = new Coordenada(currentX, currentY - 1);
                        if (grid.getPixel(upCoord) == originalColor) {
                            grid.setPixel(upCoord, newColor);
                            visited[upIdx] = true;
                            coordsToVisit.push(upIdx);
                        }
                    }
                }

                if (currentY < height - 1) {
                    int downIdx = currentIdx + width;
                    if (!visited[downIdx]) {
                        Coordenada downCoord = new Coordenada(currentX, currentY + 1);
                        if (grid.getPixel(downCoord) == originalColor) {
                            grid.setPixel(downCoord, newColor);
                            visited[downIdx] = true;
                            coordsToVisit.push(downIdx);
                        }
                    }
                }
            }

            System.out.println(System.currentTimeMillis() - startTime + " ms");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}