package lista1_tarefas;

import java.awt.Point;

class Peca extends Point {
    public static final int BRANCA = 0, PRETA = 1, VAZIA = 2;
    public int cor;

    public Peca(int xcol, int ylin, int cor) {
        this.x = xcol;
        this.y = ylin;
        this.cor = cor;
    }
}

public class ListaTeste {

    public Peca[][] criarMatriz() {
        Peca[][] tabuleiro88 = new Peca[8][8];
        for (int lin = 0; lin < 8; lin++) {
            for (int col = 0; col < 8; col++) {
                tabuleiro88[lin][col] = new Peca(lin, col, (lin + col) % 3);
            }
        }
        return tabuleiro88;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ListaTeste main = new ListaTeste();
        var m = main.criarMatriz();
        System.out.println(m.length);
        System.out.println(m[0].length);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");

        int[] c = {0, 0, 0};

        for (int col = 0; col < 8; col++)
            for (int lin = 0; lin < 8; lin++) {
                System.out.println(m[col][lin].cor);
                c[m[col][lin].cor]++;
            }

        System.out.println("" + (c[0] > 0) + (c[1] > 0) + (c[2] > 0));

    }

}
