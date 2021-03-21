package pecas;

import regras.RegraPeao;
import regras.Status;
import tabuleiro.TabuleiroDesenho;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Peao extends PecaDesenho {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Peao(int xcol, int ylin, int cor, ImageIcon img, TabuleiroDesenho tabuleiro) {
        super(xcol, ylin, cor, img, tabuleiro);
        regra = new RegraPeao();
    }

    public List<PointStatus> getMovimentosPreCalculados() {

        int posNeg = this.cor == Peca.BRANCO ? -1 : 1;
        int posInicial = this.cor == Peca.BRANCO ? 6 : 1;

        List<Peca> lista = new ArrayList<>();
        lista.add(tabuleiro.getItem(this.x, this.y + 1 * posNeg));
        lista.add(tabuleiro.getItem(this.x - posNeg, this.y + posNeg));
        lista.add(tabuleiro.getItem(this.x + posNeg, this.y + posNeg));
        if (posInicial == this.y) {
            lista.add(tabuleiro.getItem(this.x, this.y + 2 * posNeg));
        }
        return lista.stream()
                .map(p -> new PointStatus(p, regra.checkLocal(this, p, tabuleiro)))
                .filter(p -> p.status != Status.IMPEDIDO && p.status != Status.GUARDA)
                .collect(Collectors.toList());
    }
}
