package regras;

import pecas.*;
import regras.exception.MovimentoInvalidoException;
import regras.exception.RoqueException;
import tabuleiro.TabuleiroRegra;

import java.util.List;
import java.util.ArrayList;

public class RegraRei implements Regra {
    private TabuleiroRegra tabuleiro;

    public RegraRei(TabuleiroRegra tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public void checarMovimentoDaPeca(Peca pecaOrigem, Peca pecaDestino) throws MovimentoInvalidoException, RoqueException {
        // TODO Auto-generated method stub
        // Tarefa para semana que vem
        // dica: origem.distance(destino);
        /**
         @Roque se rei tiver na casa inicial da sua cor pode trocar (e não tiver sido movido)
         com qualquer uma das torres somente uma vez e se não houver peças
         entre as casas, por exemplo se tiver a torre na sua posicao inicial,
         e nao tiver cavalo, bispo e rainha
         +x roque curto
         -x roque grande
         No roque o rei anda duas casas para qualquer lado!
          * Movimento do rei não pode ser em uma coluna atacada.
          * 0 verificar se na ortogonal tem uma torre ou rainha
          * 1 verificar se na diagonal tem um bispo ou uma rainha
          * 3 verificar se tem um possivel ataque de cavalo
          * 4 verificar se tem um peao que ataque
          * essas verificações são no percurso do roque
          * ou seja nas casa que o rei ira passar!
         *
         */
        Rei rei = (Rei) pecaOrigem;

        int posX = 4;
        int posY = pecaOrigem.cor == Peca.BRANCO ? 7 : 0;

        if (pecaOrigem.x == posX && pecaOrigem.y == posY && !rei.isRoque()) {

            List<Peca> listaE = new ArrayList<>();
            List<Peca> listaD = new ArrayList<>();
            Peca peca0 = tabuleiro.getItem(0, posY);
            boolean esquerdaOK = false;
            boolean direitaOK = false;

            if (peca0 instanceof Torre && !peca0.isAlterado()) {
                esquerdaOK = true;

                for (int x = pecaOrigem.x - 1; x > 0; x--) {
                    peca0 = tabuleiro.getItem(x, posY);
                    if (peca0 instanceof Vazia)
                        listaE.add(peca0);
                    else {
                        esquerdaOK = false;
                        break;
                    }
                }

            }
            peca0 = tabuleiro.getItem(7, posY);
            if (peca0 instanceof Torre && !peca0.isAlterado()) {
                direitaOK = true;

                for (int x = pecaOrigem.x + 1; x < 7; x++) {
                    peca0 = tabuleiro.getItem(x, posY);
                    if (peca0 instanceof Vazia)
                        listaD.add(peca0);
                    else {
                        direitaOK = false;
                        break;
                    }
                }

            }

            if (listaE.size() == 3) {
                // se entrar quer dizer que não a peças no percurso
                // verificar as duas peças pra esquerda
                esquerdaOK = listaE.stream().filter(p-> checkImpedimento(p, pecaOrigem)).count() == listaE.size();
            }

            if (listaD.size() == 2) {
                // se entrar quer dizer que não a peças no percurso
                // verificar as duas pra direita
                direitaOK = listaD.stream().filter(p-> checkImpedimento(p, pecaOrigem)).count() == listaD.size();
            }

            if ((esquerdaOK && pecaOrigem.x - 2 == pecaDestino.x && pecaOrigem.y == pecaDestino.y)
                    || (direitaOK && pecaOrigem.x + 2 == pecaDestino.x && pecaOrigem.y == pecaDestino.y)) {

                throw new RoqueException();
            }
        }

        if ((pecaOrigem.distance(pecaDestino) < 2)
                && (this.isDiagonal(pecaOrigem, pecaDestino)
                || this.isOrtogonal(pecaOrigem, pecaDestino))) {
            return;
        }

        throw new MovimentoInvalidoException();
    }

    private boolean checkImpedimento(Peca p, Peca pecaOrigem) {
        List<Peca> listaCavalo = new ArrayList<>();
        Peca peca0;
        int x, y;

        // CIMA
        listaCavalo.add(tabuleiro.getItem(p.x - 1, p.y - 2));
        listaCavalo.add(tabuleiro.getItem(p.x + 1, p.y - 2));
        // DIREITA
        listaCavalo.add(tabuleiro.getItem(p.x + 2, p.y - 1));
        listaCavalo.add(tabuleiro.getItem(p.x + 2, p.y + 1));
        // BAIXO
        listaCavalo.add(tabuleiro.getItem(p.x - 1, p.y + 2));
        listaCavalo.add(tabuleiro.getItem(p.x + 1, p.y + 2));
        // ESQUERDA
        listaCavalo.add(tabuleiro.getItem(p.x - 2, p.y - 1));
        listaCavalo.add(tabuleiro.getItem(p.x - 2, p.y + 1));

        boolean existeImpedimento = !listaCavalo.stream()
                .filter(p1 -> p1 instanceof Cavalo && p1.cor != pecaOrigem.cor)
                .findFirst().isPresent();


        // ORTOGONAL
        for (y = p.y + 1; y < 8; y++) {
            peca0 = tabuleiro.getItem(p.x, y);
            if (peca0 instanceof Torre && pecaOrigem.cor != peca0.cor) {
                existeImpedimento = false;
                break;
            }
            if (!(peca0 instanceof Vazia)) break;
        }
        for (y = p.y - 1; y > -1; y--) {
            peca0 = tabuleiro.getItem(p.x, y);
            if (peca0 instanceof Torre && pecaOrigem.cor != peca0.cor) {
                existeImpedimento = false;
                break;
            }
            if (!(peca0 instanceof Vazia)) break;

        }
        for (x = p.x + 1; x < 8; x++) {
            peca0 = tabuleiro.getItem(x, p.y);
            if (peca0 instanceof Torre && pecaOrigem.cor != peca0.cor) {
                existeImpedimento = false;
                break;
            }
            if (!(peca0 instanceof Vazia)) break;
        }
        for (x = p.x - 1; x > -1; x--) {
            peca0 = tabuleiro.getItem(x, p.y);
            if (peca0 instanceof Torre && pecaOrigem.cor != peca0.cor) {
                existeImpedimento = false;
                break;
            }
            if (!(peca0 instanceof Vazia)) break;
        }

        // DIAGONAL

        for (y = p.y + 1, x = p.x + 1; y < 8 && x < 8; y++, x++) {
            peca0 = tabuleiro.getItem(x, y);
            if (pecaOrigem.cor != peca0.cor
                    && (peca0 instanceof Bispo
                    || (x == p.x + 1 && y == p.y + 1 && peca0 instanceof Peao))) {
                existeImpedimento = false;
                break;
            }
            if (!(peca0 instanceof Vazia)) break;
        }

        for (y = p.y + 1, x = p.x - 1; y < 8 && x > -1; y++, x--) {
            peca0 = tabuleiro.getItem(x, y);
            if (pecaOrigem.cor != peca0.cor
                    && (peca0 instanceof Bispo
                    || (x == p.x - 1 && y == p.y + 1 && peca0 instanceof Peao))) {
                existeImpedimento = false;
                break;
            }
            if (!(peca0 instanceof Vazia)) break;
        }

        for (y = p.y - 1, x = p.x + 1; y > -1 && x < 8; y--, x++) {
            peca0 = tabuleiro.getItem(x, y);
            if (pecaOrigem.cor != peca0.cor
                    && (peca0 instanceof Bispo
                    || (x == p.x + 1 && y == p.y - 1 && peca0 instanceof Peao))) {
                existeImpedimento = false;
                break;
            }
            if (!(peca0 instanceof Vazia)) break;
        }

        for (y = p.y - 1, x = p.x - 1; y > -1 && x > -1; y--, x--) {
            peca0 = tabuleiro.getItem(x, y);
            if (pecaOrigem.cor != peca0.cor
                    && (peca0 instanceof Bispo
                    || (x == p.x - 1 && y == p.y - 1 && peca0 instanceof Peao))) {
                existeImpedimento = false;
                break;
            }
            if (!(peca0 instanceof Vazia)) break;
        }
        return existeImpedimento;
    }

}
