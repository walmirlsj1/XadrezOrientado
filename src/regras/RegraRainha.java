package regras;

import pecas.Peca;
import regras.exception.MovimentoInvalidoException;

public class RegraRainha implements Regra {

    public void checarMovimentoDaPeca(Peca pecaOrigem, Peca pecaDestino) throws MovimentoInvalidoException {
        // TODO Auto-generated method stub
        if (this.isDiagonal(pecaOrigem, pecaDestino) || this.isOrtogonal(pecaOrigem, pecaDestino))
            return;
        throw new MovimentoInvalidoException();
    }

}
