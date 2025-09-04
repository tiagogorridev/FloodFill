import graph.ImageUtil;
import grid.Grid;

public class Main {

    // INSIRA O CAMINHO DA IMAGEM AQUI
    private static final String IMAGE_PATH = "seu_caminho_aqui/image.png";

    public static void main(String[] args) {
        
        // Colocando passo a passo para melhor entendimento
        
        ImageUtil.showImage(IMAGE_PATH);
        // Gerando grid para trabalhar
        Grid grid = gerarGrid();

        // TODO implementar metodo para coletar ou gerar o ponto de inicio
        int[] coordenadas = {0,0};

        while (!grid.estaPintado()) {

            pintarParticao(grid, coordenadas, coordenadas);
            
        }

    }

    private static Grid gerarGrid(){

        // TODO implementar gerador de grid da imagem
        return null;

    }

    private static void pintarParticao(Grid grid, int[] coordenadas, Object novoValor){

        // TODO implementar flood fill

    }

    private static int[] buscaNovasCoordenadas(Grid grid, int[] coordenadas){

        // TODO implementar busca de novas coordenadas
        return null;

    }
    
}