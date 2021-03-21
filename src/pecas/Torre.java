package pecas;

import regras.RegraTorre;
import tabuleiro.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Torre extends PecaDesenho {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Torre(int xcol, int ylin, int cor, ImageIcon img, TabuleiroDesenho tabuleiro) {
        super(xcol, ylin, cor, img, tabuleiro);
        regra = new RegraTorre();
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

        for (int i = this.y + 1; i < 8; i++) {
            peca0 = tabuleiro.getItem(this.x, i);
            if (peca0.cor != this.cor)
                lista.add(peca0);
            if (!(peca0 instanceof Vazia)) break;

        }
        for (int i = this.y - 1; i > -1; i--) {
            peca0 = tabuleiro.getItem(this.x, i);
            if (peca0.cor != this.cor || (peca0 instanceof Vazia))
                lista.add(peca0);
            if (!(peca0 instanceof Vazia)) break;

        }
        for (int i = this.x + 1; i < 8; i++) {
            peca0 = tabuleiro.getItem(i, this.y);
            if (peca0.cor != this.cor)
                lista.add(peca0);
            if (!(peca0 instanceof Vazia)) break;
        }
        for (int i = this.x - 1; i > -1; i--) {
            peca0 = tabuleiro.getItem(i, this.y);
            if (peca0.cor != this.cor)
                lista.add(peca0);
            if (!(peca0 instanceof Vazia)) break;
        }

        return lista.stream()
                .map(p -> new PointStatus(p, regra.checkLocal(this, p, tabuleiro)))
                .collect(Collectors.toList());
    }

}
