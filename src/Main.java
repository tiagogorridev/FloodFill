import grid.CoresEnum;
import grid.Grid;
import coordenadas.Coordenada;
import exception.GridException;
import floodfill.FloodFill;

public class Main {
    private static final String IMAGE_PATH = "src/assets/image.png";
    private static final String IMAGE_PATH_PAINTED_LIST = "src/assets/resultado_fila.png";
    private static final String IMAGE_PATH_PAINTED_STACK = "src/assets/resultado_pilha.png";
    
    public static void main(String[] args) {
        System.out.println("Iniciando pintura do arquivo em: " + IMAGE_PATH);
        
        Grid grid = gerarGrid();
        System.out.println("Grid: " + grid);

        FloodFill floodFill = new FloodFill(grid);
        
        Coordenada coordenadas = new Coordenada(400, 400);
        System.out.println("Iniciando pintura na coordenada " + coordenadas);
        
        int corInicial = grid.getPixel(coordenadas);
        System.out.println("Cor do ponto inicial: " + corInicial);
        
        pintarComPilha(grid, floodFill, coordenadas);

        //Recarrega grid para fila
        grid = gerarGrid();
        floodFill = new FloodFill(grid);
        
        pintarComFila(grid, floodFill, coordenadas);
    }
    
    private static Grid gerarGrid() {
        try {
            Grid novoGrid = new Grid(IMAGE_PATH);
            System.out.println("Grid criado com sucesso: " + novoGrid);
            return novoGrid;
        } catch (Exception e) {
            System.err.println("Erro ao gerar grid: " + e.getMessage());
            throw new GridException("Falha na criação do grid. erro: " + e.getMessage());
        }
    }

    private static void pintarComPilha(Grid grid, FloodFill floodFill, Coordenada pontoInicial) {
        System.out.println();
        System.out.println("Realizando pintura com estrutura de dados Pilha (Azul)");

        floodFill.pintarComPilha(pontoInicial, Grid.getCor(CoresEnum.AZUL));

        grid.salvarImagem(IMAGE_PATH_PAINTED_STACK);
        System.out.println("Imagem salva: " + IMAGE_PATH_PAINTED_STACK);
        System.out.println("Imagem pintada com sucesso!");
    }

    private static void pintarComFila(Grid grid, FloodFill floodFill, Coordenada pontoInicial) {
        System.out.println();
        System.out.println("Executando a pintura da imagem com estrutura de dados Fila (Verde)");

        floodFill.pintarComFila(pontoInicial, Grid.getCor(CoresEnum.VERDE));
        
        grid.salvarImagem(IMAGE_PATH_PAINTED_LIST);
        
        System.out.println("Imagem salva: " + IMAGE_PATH_PAINTED_LIST);
        System.out.println("Imagem pintada com sucesso!");
    }

}