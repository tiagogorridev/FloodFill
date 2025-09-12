package floodfill;

import graph.ImageUtil;
import grid.Grid;

import coordenadas.Coordenada;
import fila.Queue;
import pilha.Stack;

public class FloodFill {

    private Grid grid;

    public FloodFill(Grid grid) {
        this.grid = grid;
    }

    // Pintar com pilha
    public void pintarComPilha(Coordenada pontoInicial, int novaCor) {

        ImageUtil imageUtil = ImageUtil.setupAnimation(grid.currImagePath);
        if (imageUtil == null) {
            System.out.println("Erro ao criar contexto de animação");
            return;
        }

        // Validacoes
        if (!coordenadaEhValida(pontoInicial)) {
            System.out.println("Coordenada inválida: " + pontoInicial);
            return;
        }
        
        int corOriginal = grid.getPixel(pontoInicial);
        if (corOriginal == novaCor) {
            System.out.println("Pixel já tem a cor desejada");
            return;
        }

        // Iniciar processo
        boolean[][] jaVisitou = criarMatrizDeVisitados();
        Stack<Coordenada> pilhaDeCoordenas = new Stack<>(grid.getAltura()*grid.getLargura());
        
        pilhaDeCoordenas.push(pontoInicial);
        marcarComoVisitado(jaVisitou, pontoInicial);
        
        exibirInfoInicio("PILHA", pontoInicial, corOriginal, novaCor);

        int totalPixelsPintados = 0;
        long tempoInicio = System.currentTimeMillis();

        while (!pilhaDeCoordenas.isEmpty()) {
            Coordenada coordenadaAtual = (Coordenada) pilhaDeCoordenas.pop(); // remove ultimo elemento adicionado

            pintarPixel(coordenadaAtual, novaCor);
            totalPixelsPintados++;

            mostrarProgresso(totalPixelsPintados);

            adicionarVizinhosNaPilha(pilhaDeCoordenas, jaVisitou, coordenadaAtual, corOriginal);

            imageUtil.addNewFrame(grid.gerarImagemAtualizada());
            imageUtil.draw();
        }

        exibirResultados("PILHA", totalPixelsPintados, tempoInicio);
    }

    // Pintar com fila
    public void pintarComFila(Coordenada pontoInicial, int novaCor) {
        ImageUtil animationContext = ImageUtil.setupAnimation(grid.currImagePath);
        if (animationContext == null) {
            System.out.println("Erro ao criar contexto de animação");
            return;
        }

        if (!coordenadaEhValida(pontoInicial)) {
            System.out.println("Coordenada inválida: " + pontoInicial);
            return;
        }
        
        int corOriginal = grid.getPixel(pontoInicial);
        if (corOriginal == novaCor) {
            System.out.println("Pixel já tem a cor desejada");
            return;
        }

        boolean[][] jaVisitou = criarMatrizDeVisitados();
        Queue<Coordenada> filaDeCoordenas = new Queue<>(grid.getAltura()*grid.getLargura());
        
        filaDeCoordenas.push(pontoInicial);
        marcarComoVisitado(jaVisitou, pontoInicial);
        
        exibirInfoInicio("FILA", pontoInicial, corOriginal, novaCor);

        int totalPixelsPintados = 0;
        long tempoInicio = System.currentTimeMillis();
        
        while (!filaDeCoordenas.isEmpty()) {
            Coordenada coordenadaAtual = (Coordenada) filaDeCoordenas.pop();
            
            pintarPixel(coordenadaAtual, novaCor);
            totalPixelsPintados++;
            
            mostrarProgresso(totalPixelsPintados);
            
            adicionarVizinhosNaFila(filaDeCoordenas, jaVisitou, coordenadaAtual, corOriginal);

            animationContext.addNewFrame(grid.gerarImagemAtualizada());
            animationContext.draw();
        }
        
        exibirResultados("FILA", totalPixelsPintados, tempoInicio);
    }

    // Metodos auxiliares pilha
    private void adicionarVizinhosNaPilha(Stack<Coordenada> pilha, boolean[][] jaVisitou, 
                                         Coordenada coordenadaAtual, int corQueEstamoPintando) {
        
        Coordenada[] vizinhos = coordenadaAtual.getVizinhos();
        
        for (Coordenada vizinho : vizinhos) {
            if (deveProcessarVizinho(vizinho, jaVisitou, corQueEstamoPintando)) {
                pilha.push(vizinho);
                marcarComoVisitado(jaVisitou, vizinho);
            }
        }
    }

    // Metodos auxiliares fila
    private void adicionarVizinhosNaFila(Queue<Coordenada> fila, boolean[][] jaVisitou, Coordenada coordenadaAtual, int corQueEstamoPintando) {
        
        Coordenada[] vizinhos = coordenadaAtual.getVizinhos();
        
        for (Coordenada vizinho : vizinhos) {
            if (deveProcessarVizinho(vizinho, jaVisitou, corQueEstamoPintando)) {
                fila.push(vizinho);
                marcarComoVisitado(jaVisitou, vizinho);
            }
        }
    }

    // Metodos auxiliares gerais
    private boolean coordenadaEhValida(Coordenada coord) {
        return grid.isCoordenadasValidas(coord);
    }

    private boolean[][] criarMatrizDeVisitados() {
        return new boolean[grid.getLargura()][grid.getAltura()];
    }

    private void marcarComoVisitado(boolean[][] jaVisitou, Coordenada coord) {
        jaVisitou[coord.getX()][coord.getY()] = true;
    }

    private boolean jaFoiVisitado(boolean[][] jaVisitou, Coordenada coord) {
        return jaVisitou[coord.getX()][coord.getY()];
    }

    private void pintarPixel(Coordenada coord, int novaCor) {
        grid.setPixel(coord, novaCor);
    }

    private boolean pixelTemCorOriginal(Coordenada coord, int corOriginal) {
        return grid.getPixel(coord) == corOriginal;
    }

    private boolean deveProcessarVizinho(Coordenada vizinho, boolean[][] jaVisitou, int corOriginal) {
        return coordenadaEhValida(vizinho) && 
               !jaFoiVisitado(jaVisitou, vizinho) && 
               pixelTemCorOriginal(vizinho, corOriginal);
    }

    // Logs
    private void exibirInfoInicio(String tipoEstrutura, Coordenada pontoInicial, 
                                 int corOriginal, int novaCor) {
        System.out.println("\n=== INICIANDO FLOOD FILL COM " + tipoEstrutura + " ===");
        System.out.println("Ponto inicial: " + pontoInicial);
        System.out.println("Cor original: " + corOriginal);
        System.out.println("Nova cor: " + novaCor);
        System.out.println("Pintando...\n");
    }

    private void mostrarProgresso(int pixelsPintados) {
        if (pixelsPintados % 2000 == 0) {
            System.out.println("Progresso: " + pixelsPintados + " pixels pintados");
        }
    }

    private void exibirResultados(String tipoEstrutura, int totalPixels, long tempoInicio) {
        long tempoTotal = System.currentTimeMillis() - tempoInicio;
        System.out.println("\n=== RESULTADO " + tipoEstrutura + " ===");
        System.out.println("Total de pixels pintados: " + totalPixels);
        System.out.println("Tempo de execução: " + tempoTotal + "ms");
        System.out.println("Pintura concluída!\n");
    }
}