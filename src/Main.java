import grid.Grid;

public class Main {
    public static void main(String[] args) {
        
        // Colocando passo a passo para melhor entendimento
        
        // Gerando grid para trabalhar
        Grid grid = gerarGrid();

        // TODO implementar metodo para coletar ou gerar o ponto de inicio
        int[] coordenadas = {0,0};

        while (!grid.isFull()) {

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