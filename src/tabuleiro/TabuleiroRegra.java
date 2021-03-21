package tabuleiro;

import java.awt.Point;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import pecas.*;

public class TabuleiroRegra<T extends PecaRegra> extends LinkedList<T> implements Tabuleiro<T> {
    protected final int nLin, nCol;
//    public int xeque;
    private Vazia vazia;
    public String lastStatus = "";
    public List<PecaCapturada> listaCapturaBranca, listaCapturaPreta;
    public int vez = Peca.BRANCO;

    public TabuleiroRegra(int numColuna, int numLinha) {
        this.nLin = numLinha;
        this.nCol = numColuna;
        listaCapturaBranca = new ArrayList<>();
        listaCapturaPreta = new ArrayList<>();

        vazia = new Vazia();
    }

    public int getNumLin() {
        return nLin;
    }

    public int getNumCol() {
        return nCol;
    }

    public T getItem(int x, int y) {

        int i = this.indexOf(new Point(x, y));
        /**
         * Problema, quando faz getItem ele faz referencia a memoria a variavel (vazia)
         * quando é acionado novamente getItem ao nao encontrar a peca, ele move a peça vazia
         * e altera em todos que tiverem a referencia..
         * Solução: vazia = new Vazia();
         */

        vazia = new Vazia();
        vazia.move(x, y);
        return (i == -1) ? (T) vazia : this.get(i);
    }
}
