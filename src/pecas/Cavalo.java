package pecas;

import regras.RegraCavalo;
import regras.Status;
import tabuleiro.TabuleiroDesenho;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cavalo extends PecaDesenho {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Cavalo(int xcol, int ylin, int cor, ImageIcon img, TabuleiroDesenho tabuleiro) {
        super(xcol, ylin, cor, img, tabuleiro);
        regra = new RegraCavalo();
    }

    public List<PointStatus> getMovimentosPreCalculados() {
        /**
         * Cavalo faz movimentos em L,
         * 2x,1y ou 1x,2y pra direita, qualquer lado
         * //SENTIDO HORARIO
         * //CIMA
         * (-1,-2)
         * (+1,-2)
         * {0,-1;0,-2}
         *
         * //DIREITA
         * (+2,-1)
         * (+2,+1)
         * {+1,0;+2,0}
         *
         * //BAIXO
         * (-1,+2)
         * (+1,+2)
         * {0,+1;0,+2}
         *
         * //ESQUERDA
         * (-2,-1)
         * (-2,+1)
         * {-1,0;-2,0}
         *
         *
         *
         * ***** O ALVO PODE SER ATÉ 8 PEÇAS QUE ESTÃO NA PONTA DO L
         */

        ArrayList<Peca> lista = new ArrayList<>();
        // CIMA
        lista.add(tabuleiro.getItem(this.x - 1, this.y - 2));
        lista.add(tabuleiro.getItem(this.x + 1, this.y - 2));
        // DIREITA
        lista.add(tabuleiro.getItem(this.x + 2, this.y - 1));
        lista.add(tabuleiro.getItem(this.x + 2, this.y + 1));
        // BAIXO
        lista.add(tabuleiro.getItem(this.x - 1, this.y + 2));
        lista.add(tabuleiro.getItem(this.x + 1, this.y + 2));
        // ESQUERDA
        lista.add(tabuleiro.getItem(this.x - 2, this.y - 1));
        lista.add(tabuleiro.getItem(this.x - 2, this.y + 1));

        return lista.stream()
                .map(p -> new PointStatus(p, regra.checkLocal(this, p, tabuleiro)))
                .filter(p -> p.status != Status.IMPEDIDO && p.status != Status.GUARDA)
                .collect(Collectors.toList());
    }
}
