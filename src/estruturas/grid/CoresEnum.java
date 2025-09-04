package grid;

public enum CoresEnum {

    // Cores predefinidas para uso no flood fill

    BRANCO(255, 255, 255), // FFFFFF
    AMARELO(255, 255, 0), // FFFF00
    VERMELHO(255, 0, 0), // FF0000
    VERDE(0, 255, 0), // 00FF00
    AZUL(0, 0, 255), // 0000FF
    PRETO(0, 0, 0) // 000000
    ;

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
