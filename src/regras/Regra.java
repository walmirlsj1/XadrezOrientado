package regras;

import java.awt.Point;
import java.util.List;
import java.util.Vector;

import pecas.Peca;
import pecas.XEnPassant;
import regras.exception.*;
import tabuleiro.Tabuleiro;
import tabuleiro.TabuleiroRegra;

public interface Regra {


    default Status checkLocal(Peca origem, Peca destino, TabuleiroRegra tabuleiro) {

        try {

            checarLimitesDoTabuleiro(destino);
            checarOrigemDiferenteDoDestino(origem, destino);
            checarPercurso(origem, destino, tabuleiro); // não permite colisão
            checarMovimentoDaPeca(origem, destino);
//            checarAutoXeque(origem, destino, tabuleiro);
            checarDestinoOcupado(origem, destino);
            return Status.LIVRE;
//        } catch (PromocaoPeaoException e) {
//            /** problema é que o peao pode ter dois estados, ao mesmo tempo
//             *  Promoção e Captura...
//             */
//            return Status.PROMOCAO;
        } catch (PassoDuploDoPeaoException e) {
            return Status.PASSO_DUPLO;
        } catch (EnPassantException e) {
            return Status.EN_PASSANT;
        } catch (RoqueException e) {
            return Status.ROQUE;
//        } catch (XequeException e) {
//            return Status.XEQUE;
        } catch (CapturaOponenteException e) {
            return Status.CAPTURA;
        } catch (ProtegeAliadoException e) {
            return Status.GUARDA;
        } catch (MovimentoNaoPermitidosException e) {
            return Status.IMPEDIDO;
        }
    }


    default void checarLimitesDoTabuleiro(Point destino)
            throws ForaDoTabuleiroException {

        if (destino.x < 0 || destino.x > 7 || destino.y < 0 || destino.y > 7)
            throw new ForaDoTabuleiroException();

    }

    default void checarOrigemDiferenteDoDestino(Peca origem, Peca destino) throws OrigemIgualDestinoException {
        if (0.0f != origem.distance(destino)) return;
        throw new OrigemIgualDestinoException();
    }

    default List<Point> getPointsEntre(Point origem, Point destino) {
        int dx = destino.x - origem.x;
        int dy = destino.y - origem.y;
        if (dx != 0) dx /= Math.abs(dx);
        if (dy != 0) dy /= Math.abs(dy);

        var p = new Point(origem.x, origem.y);
        p.translate(dx, dy);
        var list = new Vector<Point>();
        while (!destino.equals(p)) {
            list.add(new Point(p));
            p.translate(dx, dy);

        }
        return list;
    }

    default void checarPercurso(Peca origem, Peca destino, Tabuleiro tabuleiro) throws MovimentoInvalidoException, PercursoObstruidoException {
        // não permite colisão
        if (!isDiagonal(origem, destino) && !isOrtogonal(origem, destino))
            throw new MovimentoInvalidoException();

        var cont = getPointsEntre(origem, destino).stream()
                .map(tabuleiro::indexOf)
                .filter(i -> i >= 0)
                .count();
        if (cont > 0)
            throw new PercursoObstruidoException();


    }

    void checarMovimentoDaPeca(Peca origem, Peca destino) throws MovimentoInvalidoException, CapturaOponenteException, PassoDuploDoPeaoException, EnPassantException, ProtegeAliadoException, RoqueException;

    default void checarAutoXeque(Peca origem, Peca destino, TabuleiroRegra tabuleiro) {
        return;
    }

    default void checarDestinoOcupado(Peca origem, Peca destino) throws CapturaOponenteException, ProtegeAliadoException {
        if (destino.cor == Peca.VAZIO || destino instanceof XEnPassant) return;
        if (origem.cor == destino.cor) throw new ProtegeAliadoException();

        throw new CapturaOponenteException();
    }

    default boolean isOrtogonal(Point pecaOrigem, Point pecaDestino) {
        return pecaOrigem.getX() == pecaDestino.getX() || pecaOrigem.getY() == pecaDestino.getY();
    }

    default boolean isDiagonal(Point pecaOrigem, Point pecaDestino) {
        // mod(lin+col;2); // matriz
        // lin + col == � 1 diagonal || lin - col == � a outra diagonal
        return pecaOrigem.getX() + pecaOrigem.getY() == pecaDestino.getX() + pecaDestino.getY()
                || pecaOrigem.getX() - pecaOrigem.getY() == pecaDestino.getX() - pecaDestino.getY();
    }
}
