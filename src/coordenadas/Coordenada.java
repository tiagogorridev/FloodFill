package coordenadas;

/* Classe para representar uma coordenada no grid */
public class Coordenada {
    
    private int x;
    private int y;
    
    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    /* Verifica se a coordenada é valida dentro dos limites do grid */
    public boolean isValida(int largura, int altura) {
        return x >= 0 && x < largura && y >= 0 && y < altura;
    }
    
    /* Retorna os 4 vizinhos laterais desta coordenada */
    public Coordenada[] getVizinhos() {
        return new Coordenada[] {
            new Coordenada(x, y - 1),
            new Coordenada(x, y + 1),
            new Coordenada(x - 1, y),
            new Coordenada(x + 1, y)
        };
    }
    
    /* Comparação entre coordenadas */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coordenada that = (Coordenada) obj;
        return x == that.x && y == that.y;
    }
    
    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}