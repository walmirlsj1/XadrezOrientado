package regras;

import pecas.Peca;
import pecas.XEnPassant;
import regras.exception.*;

public class RegraPeao implements Regra {

    public void checarMovimentoDaPeca(Peca pecaOrigem, Peca pecaDestino) throws MovimentoInvalidoException, CapturaOponenteException, PassoDuploDoPeaoException, ProtegeAliadoException, EnPassantException {
        // TODO Auto-generated method stub

        int inc = pecaOrigem.cor == Peca.BRANCO ? -1 : +1; //Peca.Branca = 0; Peca.Preta = 1;

        int posInicial = pecaOrigem.cor == Peca.BRANCO ? 6 : 1;

        /** Promocao do peao, talves esteja no lugar, errado.
         * Foi tratado fora das regras.
                int y = pecaOrigem.cor == Peca.BRANCO ? 0 : 7;
                if(pecaOrigem.y == y)
                    throw new PromocaoPeaoException();
        */

        if (pecaDestino.cor == Peca.VAZIO) {
            if (pecaOrigem.getX() == pecaDestino.getX() && pecaOrigem.getY() + inc == pecaDestino.getY()) return;

            if (pecaOrigem.getY() == posInicial && (pecaOrigem.getX() == pecaDestino.getX() && pecaOrigem.getY() + (inc * 2) == pecaDestino.getY()))
                throw new PassoDuploDoPeaoException();
        } else {
            if (pecaOrigem.getX() == pecaDestino.getX() && pecaOrigem.getY() + inc == pecaDestino.getY() && pecaDestino instanceof XEnPassant)
                return;

            if ((pecaOrigem.x + inc == pecaDestino.x || pecaOrigem.x - inc == pecaDestino.x)
                    && pecaOrigem.getY() + inc == pecaDestino.getY()) {

                if (pecaOrigem.cor != pecaDestino.cor) {
                    if (pecaDestino instanceof XEnPassant)
                        throw new EnPassantException();
                    throw new CapturaOponenteException();
                }
                throw new ProtegeAliadoException();
            }
        }
        throw new MovimentoInvalidoException();


        /**
         *
         * na linha 1 pode andar duas casas pra frente
         * apartir da linha 2 so pode andar uma posicao
         * os pretos so vai pra baixo
         * os brancos so vao pra cima
         * peao so captura em diagonal
         * peao preto linha ++
         * peao branco linha --
         * se peao preto na linha 1 pode linha+=2
         * se peao branco na linha 6 pode linha-=2;
         * captura na diagonal linha +1 col +-1
         * EN_Passant é a sombra deixada pelo peao, ao andar duas casa
         * Por exemplo se o peao branco sai da casa 6 e vai pra case 4,
         * a casa 5 fica a sombra dele, se tiver algum peao preto na diagonal,
         * ele pode ver e matar o peao branco que deixou a sombra.
         */
    }

}
