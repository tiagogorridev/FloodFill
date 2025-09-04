import graph.ImageUtil;
import grid.Grid;
import coordenadas.Coordenada;
import floodfill.FloodFill;

public class Main {
    // INSIRA O CAMINHO DA IMAGEM AQUI
    private static final String IMAGE_PATH = "src/assets/image.png";
    
    public static void main(String[] args) {
        // Mostrar imagem original
        ImageUtil.showImage(IMAGE_PATH);
        
        // Gerando grid para trabalhar
        Grid grid = gerarGrid();
        FloodFill floodFill = new FloodFill(grid);
        
        // Coordenadas de início
        Coordenada pontoInicial = new Coordenada(500, 500);
        System.out.println("=== INICIANDO TESTE DE PINTURA ===");
        System.out.println("Grid: " + grid);
        System.out.println("Coordenada inicial: " + pontoInicial);
        
        // Debug: verificar cor do ponto inicial
        int corInicial = grid.getPixel(pontoInicial);
        System.out.println("Cor do ponto inicial: " + corInicial);
        
        // Teste com PILHA - pinta de vermelho
        System.out.println("\n--- TESTE COM PILHA (VERMELHO) ---");
        floodFill.floodFillComPilha(pontoInicial, Grid.Cores.VERMELHO);
        grid.salvarImagem("resultado_pilha.png");
        System.out.println("Imagem salva: resultado_pilha.png");
        ImageUtil.showImage("resultado_pilha.png");
        
        // Recarrega grid para teste com fila
        grid = gerarGrid();
        floodFill = new FloodFill(grid);
        
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