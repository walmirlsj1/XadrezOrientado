package regras;

import pecas.Peca;
import regras.exception.MovimentoInvalidoException;
import regras.exception.PercursoObstruidoException;
import tabuleiro.Tabuleiro;

public class RegraCavalo implements Regra {

    public void checarMovimentoDaPeca(Peca pecaOrigem, Peca pecaDestino) throws MovimentoInvalidoException {
        // TODO Auto-generated method stub
//		if(this.isDiagonal(origem, destino)) return true;
        var d = pecaOrigem.distance(pecaDestino);
        if (d > 2 && d < 2.5) {
            return;
        }
        throw new MovimentoInvalidoException();
    }

    @Override
    public void checarPercurso(Peca origem, Peca destino, Tabuleiro tabuleiro) throws MovimentoInvalidoException, PercursoObstruidoException {
        // não verifica
    }


}
