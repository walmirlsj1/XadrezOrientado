package pecas;

import regras.RegraRei;
import regras.Status;
import tabuleiro.TabuleiroDesenho;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Rei extends PecaDesenho {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private boolean roque = false;

    public Rei(int xcol, int ylin, int cor, ImageIcon img, TabuleiroDesenho tabuleiro) {
        super(xcol, ylin, cor, img, tabuleiro);
        regra = new RegraRei(tabuleiro);
    }

    public List<PointStatus> getMovimentosPreCalculados() {
        /**
         * Rei faz movimentos para todos os lados assim como a rainha,
         * ******* porem uma casa só
         */

        ArrayList<Peca> lista = new ArrayList<>();

        lista.add(tabuleiro.getItem(this.x - 1, this.y - 1));
        lista.add(tabuleiro.getItem(this.x - 1, this.y));
        lista.add(tabuleiro.getItem(this.x - 1, this.y + 1));

        lista.add(tabuleiro.getItem(this.x, this.y - 1));
        lista.add(tabuleiro.getItem(this.x, this.y + 1));

        lista.add(tabuleiro.getItem(this.x + 1, this.y - 1));
        lista.add(tabuleiro.getItem(this.x + 1, this.y));
        lista.add(tabuleiro.getItem(this.x + 1, this.y + 1));

        lista.add(tabuleiro.getItem(this.x + 2, this.y));
        lista.add(tabuleiro.getItem(this.x - 2, this.y));

        return lista.stream()
                .map(p -> new PointStatus(p, regra.checkLocal(this, p, tabuleiro)))
                .filter(p -> p.status != Status.IMPEDIDO && p.status != Status.GUARDA)
                .collect(Collectors.toList());
    }

    public void setRoque() {
        this.roque = true;
    }

    public boolean isRoque() {
        return roque;
    }
}
