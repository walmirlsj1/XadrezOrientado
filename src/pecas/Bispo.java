package pecas;

import regras.RegraBispo;
import tabuleiro.TabuleiroDesenho;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bispo extends PecaDesenho {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Bispo(int xcol, int ylin, int cor, ImageIcon img, TabuleiroDesenho tabuleiro) {
        super(xcol, ylin, cor, img, tabuleiro);
        regra = new RegraBispo();
    }

    public List<PointStatus> getMovimentosPreCalculados() {
        /**
         * Bispo faz movimentos na diagonal
         *
         */

        ArrayList<Peca> lista = new ArrayList<>();
        Peca peca0 = null;
        int x, y;

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
