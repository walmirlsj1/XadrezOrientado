package pecas;

import regras.RegraRainha;
import tabuleiro.TabuleiroDesenho;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Rainha extends PecaDesenho {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Rainha(int xcol, int ylin, int cor, ImageIcon img, TabuleiroDesenho tabuleiro) {
        super(xcol, ylin, cor, img, tabuleiro);
        regra = new RegraRainha();
    }

    public List<PointStatus> getMovimentosPreCalculados() {
        /**
         * Torre faz movimentos em | __,
         * 0x,1y ou xx,-1y pra direita, qualquer lado
         * 1,0 -1,0
         * ***** O ALVO PODE SER ATÉ 8 PEÇAS QUE ESTÃO NA PONTA DO L
         */

        ArrayList<Peca> lista = new ArrayList<>();
        Peca peca0;
        int x, y;

        // ORTOGONAL

        for (y = this.y + 1; y < 8; y++) {
            peca0 = tabuleiro.getItem(this.x, y);
            if (peca0.cor != this.cor)
                lista.add(peca0);
            if (!(peca0 instanceof Vazia)) break;

        }
        for (y = this.y - 1; y > -1; y--) {
            peca0 = tabuleiro.getItem(this.x, y);
            if (peca0.cor != this.cor || (peca0 instanceof Vazia))
                lista.add(peca0);
            if (!(peca0 instanceof Vazia)) break;

        }
        for (x = this.x + 1; x < 8; x++) {
            peca0 = tabuleiro.getItem(x, this.y);
            if (peca0.cor != this.cor)
                lista.add(peca0);
            if (!(peca0 instanceof Vazia)) break;
        }
        for (x = this.x - 1; x > -1; x--) {
            peca0 = tabuleiro.getItem(x, this.y);
            if (peca0.cor != this.cor)
                lista.add(peca0);
            if (!(peca0 instanceof Vazia)) break;
        }

        // DIAGONAL

        for (y = this.y + 1, x = this.x + 1; y < 8 && x < 8; y++, x++) {
            peca0 = tabuleiro.getItem(x, y);
            if (peca0.cor != this.cor) lista.add(peca0);
            if (!(peca0 instanceof Vazia)) break;
        }

        for (y = this.y + 1, x = this.x - 1; y < 8 && x > -1; y++, x--) {
            peca0 = tabuleiro.getItem(x, y);
            if (peca0.cor != this.cor) lista.add(peca0);
            if (!(peca0 instanceof Vazia)) break;
        }

        for (y = this.y - 1, x = this.x + 1; y > -1 && x < 8; y--, x++) {
            peca0 = tabuleiro.getItem(x, y);
            if (peca0.cor != this.cor) lista.add(peca0);
            if (!(peca0 instanceof Vazia)) break;
        }

        for (y = this.y - 1, x = this.x - 1; y > -1 && x > -1; y--, x--) {
            peca0 = tabuleiro.getItem(x, y);
            if (peca0.cor != this.cor) lista.add(peca0);
            if (!(peca0 instanceof Vazia)) break;
        }
        return lista.stream()
                .map(p -> new PointStatus(p, regra.checkLocal(this, p, tabuleiro)))
                .collect(Collectors.toList());
    }

}
