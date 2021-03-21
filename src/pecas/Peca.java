package pecas;

import tabuleiro.TabuleiroDesenho;

import java.awt.*;

public abstract class Peca extends Point {
    public static final int BRANCO = 0, PRETO = 1, VAZIO = 2;
    protected TabuleiroDesenho tabuleiro;
    public int cor;
    protected boolean alterado = false;

    public Peca(int xcol, int ylin, int cor) {
        super(xcol, ylin);
        this.cor = cor;
    }

    public boolean isVazia() {
        return cor == Peca.VAZIO;
    }

    public boolean isAlterado() {
        return this.alterado;
    }

    public void setAlterado() {
        this.alterado = true;
    }

}
