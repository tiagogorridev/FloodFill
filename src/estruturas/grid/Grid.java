package grid;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import coordenadas.Coordenada;

public class Grid {
    
    private int[][] pixels;
    private int largura;
    private int altura;
    
    // Construtor que cria grid a partir de arquivo de imagem
    public Grid(String caminhoImagem) {
        carregarImagem(caminhoImagem);
    }
    
    // Carrega imagem do arquivo para matriz de pixels
    private void carregarImagem(String caminhoImagem) {
        try {
            BufferedImage imagemOriginal = ImageIO.read(new File(caminhoImagem));
            largura = imagemOriginal.getWidth();
            altura = imagemOriginal.getHeight();
            pixels = new int[largura][altura];
            
            // Copia todos os pixels da imagem para a matriz
            for (int x = 0; x < largura; x++) {
                for (int y = 0; y < altura; y++) {
                    pixels[x][y] = imagemOriginal.getRGB(x, y);
                }
            }
            
        } catch (IOException e) {
            throw new RuntimeException("Erro ao carregar imagem: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Caminho de imagem inválido: " + caminhoImagem);
        }
    }
    
    // Retorna cor do pixel na coordenada especificada
    public int getPixel(Coordenada coord) {
        if (!coord.isValida(largura, altura)) {
            throw new IndexOutOfBoundsException("Coordenada fora dos limites: " + coord);
        }
        return pixels[coord.getX()][coord.getY()];
    }
    
    // Define nova cor para pixel na coordenada especificada
    public void setPixel(Coordenada coord, int novoRGB) {
        if (!coord.isValida(largura, altura)) {
            throw new IndexOutOfBoundsException("Coordenada fora dos limites: " + coord);
        }
        pixels[coord.getX()][coord.getY()] = novoRGB;
    }
    
    // Verifica se coordenada está dentro dos limites do grid
    public boolean isCoordenadasValidas(Coordenada coord) {
        return coord.isValida(largura, altura);
    }
    
    // Gera nova imagem a partir da matriz de pixels modificada
    public BufferedImage gerarImagemAtualizada() {
        BufferedImage novaImagem = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_RGB);
        
        // Preenche a nova imagem com os pixels da matriz
        for (int x = 0; x < largura; x++) {
            for (int y = 0; y < altura; y++) {
                novaImagem.setRGB(x, y, pixels[x][y]);
            }
        }
        
        return novaImagem;
    }
    
    public void salvarImagem(String caminhoSaida) {
        try {
            BufferedImage imagemAtualizada = gerarImagemAtualizada();
            ImageIO.write(imagemAtualizada, "png", new File(caminhoSaida));
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar imagem: " + e.getMessage());
        }
    }
    
    public int getLargura() {
        return largura;
    }
    
    public int getAltura() {
        return altura;
    }
    
    // Método simplificado - usa o toRGB() da enum diretamente
    public static int getCor(CoresEnum cor) {
        return (0xFF << 24) | cor.toRGB();
    }
    
    // Representação textual do grid
    @Override
    public String toString() {
        return "Grid[" + largura + "x" + altura + "]";
    }
}