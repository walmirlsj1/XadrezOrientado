package pecas;

import regras.*;

import java.awt.Point;
import java.util.List;

public abstract class PecaRegra extends Peca {
    public Regra regra;
    protected boolean dragdrop = false;

    public PecaRegra(int xcol, int ylin, int cor) {
        super(xcol, ylin, cor);
        //TODO tratar as regras
    }


    public List<PointStatus> getMovimentosPreCalculados() {
        return null;
    }

    public Status podeMoverAte(Point destino) {

        return regra.checkLocal(this, tabuleiro.getItem(destino.x, destino.y), tabuleiro);
    }
}
