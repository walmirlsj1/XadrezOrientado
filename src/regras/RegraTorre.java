package regras;

import pecas.Peca;
import regras.exception.MovimentoInvalidoException;

public class RegraTorre implements Regra {

    public void checarMovimentoDaPeca(Peca pecaOrigem, Peca pecaDestino) throws MovimentoInvalidoException {
        // TODO Auto-generated method stub
        if (this.isOrtogonal(pecaOrigem, pecaDestino)) return;
        throw new MovimentoInvalidoException();
    }

}
