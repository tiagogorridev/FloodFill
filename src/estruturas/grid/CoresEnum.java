package grid;

public enum CoresEnum {
    BRANCO(255, 255, 255),
    AMARELO(255, 255, 0),
    VERMELHO(255, 0, 0),
    VERDE(0, 255, 0),
    AZUL(0, 0, 255),
    PRETO(0, 0, 0);

    private final int r;
    private final int g;
    private final int b;

    CoresEnum(int r, int g, int b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public int toRGB() {
        return (r << 16) | (g << 8) | b;
    }
    
}
