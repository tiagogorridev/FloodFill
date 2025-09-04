package grid;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Grid {
    private int[][] matriz;
    private int largura;
    private int altura;

    public Grid(int width, int height) {
        this.largura = width;
        this.altura = height;
        this.matriz = new int[height][width];
    }

    public boolean estaPintado() {
        // Example: returns true if all cells are non-zero
        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < largura; x++) {
                if (matriz[y][x] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public void pintaCelula(int x, int y, int color) {
        if (x >= 0 && x < largura && y >= 0 && y < altura) {
            matriz[y][x] = color;
        } else {
            throw new IndexOutOfBoundsException("Cell coordinates out of bounds");
        }
    }

    public int pegaCorDaCelula(int x, int y) {
        if (x >= 0 && x < largura && y >= 0 && y < altura) {
            return matriz[y][x];
        } else {
            throw new IndexOutOfBoundsException("Cell coordinates out of bounds");
        }
    }

    public static Grid geraGridFromPng(String path) {
        try {
            BufferedImage imagem = ImageIO.read(new File(path));
            int larguraImagem = imagem.getWidth();
            int alturaImagem = imagem.getHeight();
            Grid grid = new Grid(larguraImagem, alturaImagem);
            for (int y = 0; y < alturaImagem; y++) {
                for (int x = 0; x < larguraImagem; x++) {
                    int rgb = imagem.getRGB(x, y);
                    grid.pintaCelula(x, y, rgb);
                }
            }
            return grid;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load image: " + e.getMessage(), e);
        }
    }
}
