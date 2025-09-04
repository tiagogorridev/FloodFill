import graph.ImageUtil;
import grid.CoresEnum;
import grid.Grid;
import coordenadas.Coordenada;
import exception.GridException;
import floodfill.FloodFill;

public class Main {
    // INSIRA O CAMINHO DA IMAGEM AQUI
    private static final String IMAGE_PATH = "src/assets/image.png";

    private static final String IMAGE_PATH_PAINTED_LIST = "src/assets/resultado_fila.png";

    private static final String IMAGE_PATH_PAINTED_STACK = "src/assets/resultado_pilha.png";
    
    public static void main(String[] args) {

        System.out.println("Iniciando pintura do arquivo em: " + IMAGE_PATH);
        
        // Mostrar imagem original
        ImageUtil.showImage(IMAGE_PATH);
        
        // Gerando grid para trabalhar
        Grid grid = gerarGrid();
        System.out.println("Grid: " + grid);

        FloodFill floodFill = new FloodFill(grid);
        
        // Coordenadas de início
        Coordenada coordenadas = new Coordenada(500, 500);
        System.out.println("Iniciando pintura na coordenada " + coordenadas);
        
        // Debug: verificar cor do ponto inicial
        int corInicial = grid.getPixel(coordenadas);
        System.out.println("Cor do ponto inicial: " + corInicial);
        
        pintarComPilha(grid, floodFill, coordenadas);

      // Recarrega grid para teste com fila
        grid = gerarGrid();
        floodFill = new FloodFill(grid);
        
        pintarComFila(grid, floodFill, coordenadas);

        System.out.println("Compare os resultados!");
    }
    
    private static Grid gerarGrid() {
        
        try {

            Grid novoGrid = new Grid(IMAGE_PATH);
            
            System.out.println("Grid criado com sucesso: " + novoGrid);

            return novoGrid;

        } catch (Exception e) {

            System.err.println("Erro ao gerar grid: " + e.getMessage());
            
            // e.printStackTrace();

            throw new GridException("Falha na criação do grid. erro: " + e.getMessage());
        
        }
    }

    private static void pintarComPilha(Grid grid, FloodFill floodFill, Coordenada pontoInicial){

        // Teste com PILHA - pinta de vermelho
        System.out.println();

        System.out.println("Realizando pintura com estrutura de dados Pilha (Vermelho)");

        floodFill.floodFillComPilha(pontoInicial, Grid.getCor(CoresEnum.VERMELHO));

        grid.salvarImagem(IMAGE_PATH_PAINTED_STACK);
        System.out.println("Imagem salva: " + IMAGE_PATH_PAINTED_STACK);

        // Exibindo imagem já colorida
        ImageUtil.showImage(IMAGE_PATH_PAINTED_STACK);

        System.out.println("Imagem pintada com sucesso!");
        
    }

    private static void pintarComFila(Grid grid, FloodFill floodFill, Coordenada pontoInicial){

        // Teste com FILA - pinta de azul  
        System.out.println();
        System.out.println("Executando a pintura da imagem com estrutura de dados Fila (Azul)");

        floodFill.floodFillComFila(pontoInicial, Grid.getCor(CoresEnum.AZUL));
        
        grid.salvarImagem(IMAGE_PATH_PAINTED_LIST);
        
        System.out.println("Imagem salva: " + IMAGE_PATH_PAINTED_LIST);
        
        ImageUtil.showImage(IMAGE_PATH_PAINTED_LIST);
        
        System.out.println("Imagem pintada com sucesso!");
        
    }

        // Teste com FILA - pinta de azul  
        System.out.println("\n--- TESTE COM FILA (AZUL) ---");
        floodFill.floodFillComQueue(pontoInicial, Grid.Cores.AZUL);
        grid.salvarImagem("resultado_fila.png");
        System.out.println("Imagem salva: resultado_fila.png");
        ImageUtil.showImage("resultado_fila.png");
        
        System.out.println("\n=== TESTE FINALIZADO ===");
        System.out.println("Compare os resultados!");
    }
    
    private static Grid gerarGrid() {
        try {
            Grid novoGrid = new Grid(IMAGE_PATH);
            System.out.println("Grid gerado com sucesso: " + 
                             novoGrid.getLargura() + "x" + novoGrid.getAltura());
            return novoGrid;
        } catch (Exception e) {
            System.err.println("Erro ao gerar grid: " + e.getMessage());
            throw new RuntimeException("Falha na criação do grid", e);
        }
    }

}