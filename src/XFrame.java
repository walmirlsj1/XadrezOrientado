
import pecas.*;
import pecas.factory.Factory;
//import regras.Regra;
import regras.Status;
import tabuleiro.*;
import utils.DoubleBufferedCanvas;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class XFrame extends JFrame {
    private int square = 70, dx = 50, enPassant = -1;
    public TabuleiroDesenho<PecaDesenho> tabuleiro;
    private PecaDesenho pecaSelecionada;
    private PecaDesenho pecaVazia;
    //    private Regra regra;
    private Metodo[] metodo;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    new XFrame().setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public XFrame() throws IOException {
        super("..:: Xadrez 3.0 ::..");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //"/img/background - Copia.jpg" vkp.png bkp_metallic-theme.jpg
        BufferedImage background = ImageIO.read(getClass().getResource("/img/background - Copia.jpg"));

        tabuleiro = new TabuleiroDesenho<PecaDesenho>(square, dx, background);

        inicializaXPecas();
        inicializaMetodos();

        tabuleiro.sort(Comparator.comparingDouble(PecaDesenho::getY));

        DoubleBufferedCanvas canvas = new DoubleBufferedCanvas() {
            @Override
            public void paint(Graphics g) {
                tabuleiro.paintIcon(this, g, 0, 0);
            }
        };

        canvas.addMouseListener(myMouseListenner());
        canvas.addMouseMotionListener(myMouseListenner()); // drag and drop

        canvas.setSize(tabuleiro.getDimension());

        this.add(canvas);
        this.pack();
        setLayout(null);

    }

    private void inicializaMetodos() {
        metodo = new Metodo[16];
        metodo[Status.LIVRE.ordinal()] = (origem, destino) -> {
            origem.move(destino.x, destino.y);
            origem.setAlterado();
            if (enPassant != tabuleiro.vez)
                tabuleiro.removeIf(p -> p instanceof XEnPassant);

//          tabuleiro.forEach(p->p.recalculaMovimentosPreCalculados());
            tabuleiro.sort(Comparator.comparingDouble(PecaDesenho::getY));
            tabuleiro.vez = (tabuleiro.vez + 1) % 2;

            return Status.IMPEDIDO;
        };
        metodo[Status.PROMOCAO.ordinal()] = (origem, destino) -> {

            String[] pecas = {"Rainha", "Torre", "Bispo", "Cavalo"};
            JOptionPane opc = new JOptionPane();
            int n = -1;
            while (n == -1) {
                n = opc.showOptionDialog(
                        null,
                        "Selecione a peça que deseja promover o Peão?",
                        "Promoção do peao",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        pecas,
                        -1);
            }
            Class[] classes = {Rainha.class, Torre.class, Bispo.class, Cavalo.class};

            ImageIcon imgPeca = new ImageIcon(
                    getClass().getResource(
                            "/img/" + pecas[n].toLowerCase() + "-" +
                                    (origem.cor == Peca.BRANCO ? "branco" : "preto") + ".png"));

            var pecaPromovida = (PecaDesenho) Factory.criar(
                    classes[n],
                    origem.x, origem.y, origem.cor,
                    imgPeca, tabuleiro);

            tabuleiro.removeIf(p -> p.equals(origem));
            tabuleiro.add(pecaPromovida);

            return Status.NADA;
        };
        metodo[Status.CAPTURA.ordinal()] = (origem, destino) -> {
            PecaDesenho pecaDestino = tabuleiro.getItem(destino.x, destino.y);

            PecaCapturada pecaCapturada = new PecaCapturada(pecaDestino.x, pecaDestino.y, pecaDestino.cor, pecaDestino.getImageIcon());
            tabuleiro.removeIf(p -> p.equals(destino));

            if (origem.cor == Peca.BRANCO) {
                tabuleiro.listaCapturaBranca.add(pecaCapturada);
            } else {
                tabuleiro.listaCapturaPreta.add(pecaCapturada);
            }

            return Status.LIVRE;
        };

        metodo[Status.PASSO_DUPLO.ordinal()] = (origem, destino) -> {
            tabuleiro.removeIf(p -> p instanceof XEnPassant);
            tabuleiro.add(new XEnPassant(origem, destino));
            enPassant = origem.cor;
            return Status.LIVRE;
        };
        metodo[Status.EN_PASSANT.ordinal()] = (origem, destino) -> {
            int inc = origem.cor != Peca.BRANCO ? -1 : +1;
            var p = new Point(destino.x, destino.y + inc);
            var i = tabuleiro.indexOf(p);
            var peao = tabuleiro.get(i);
            peao.move(destino.x, destino.y);

            tabuleiro.removeIf(p1 -> p1 instanceof XEnPassant); // corrige bug gerado por ter duas pecas no mesmo lugar
            return Status.CAPTURA;
        };
        metodo[Status.ROQUE.ordinal()] = (rei, point) -> {
            var rx = (point.x == 2) ? 2 : 6; //movimento do rei: (2)<-rei(4)->(6)
            var tx = (point.x == 2) ? 3 : 5; //movimento da torre: rei(2)torre3 ou torre(5)rei(6)
            int xt = (point.x == 6) ? 7 : 0;

            var torre = tabuleiro.getItem(xt, point.y);
            torre.move(tx, point.y);
            torre.coordFixaNoTabuleiro();
            point.move(rx, point.y);

            ((Rei) rei).setRoque();

            return Status.LIVRE;//movimento o rei
        };
        metodo[Status.IMPEDIDO.ordinal()] = (origem, destino) -> {
            pecaSelecionada.coordFixaNoTabuleiro();
            return Status.NADA;
        };
        metodo[Status.GUARDA.ordinal()] = metodo[Status.IMPEDIDO.ordinal()];
        metodo[Status.XEQUE.ordinal()] = metodo[Status.IMPEDIDO.ordinal()];
    }

    private void inicializaXPecas() {
        String[] nome = {"peao", "torre", "cavalo", "bispo", "rainha", "rei"};
        Class[] classes = {Peao.class, Torre.class, Cavalo.class, Bispo.class, Rainha.class, Rei.class};

//         teste ROQUE
//        int[] tipo = {0, 0, 0, 0, 0, 0, 1, 5, 1};
//        int[] col =  {0, 1, 2, 5, 6, 7, 0, 4, 7};
//        int[] linb = {6, 6, 6, 6, 6, 6, 7, 7, 7};
//        int[] linp = {1, 1, 1, 1, 1, 1, 0, 0, 0};
//        int[] hack = {0, 1, 2, 3, 4, 5, 6, 7, 8};

        int[] tipo = {0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 3, 2, 1};
        int[] col = {0, 1, 2, 3, 4, 5, 6, 7, 0, 1, 2, 3, 4, 5, 6, 7};
        int[] linb = {6, 6, 6, 6, 6, 6, 6, 6, 7, 7, 7, 7, 7, 7, 7, 7};
        int[] linp = {1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] hack = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

        var imgPretas = Arrays.stream(nome)
                .map(str -> "/img/" + str + "-preto.png")
                .map(str -> getClass().getResource(str))
                .map(ImageIcon::new)
                .collect(Collectors.toList());

        var imgBrancas = Arrays.stream(nome)
                .map(str -> "/img/" + str + "-branco.png")
                .map(str -> getClass().getResource(str))
                .map(ImageIcon::new)
                .collect(Collectors.toList());

        var pecaPreta = Arrays.stream(hack) //col lin
                .mapToObj(i -> (PecaDesenho) Factory.criar(
                        classes[tipo[i]], col[i], linp[i], Peca.PRETO, imgPretas.get(tipo[i]), tabuleiro))
                .collect(Collectors.toList());

        var pecaBranca = Arrays.stream(hack) //col lin
                .mapToObj(i -> (PecaDesenho) Factory.criar(
                        classes[tipo[i]], col[i], linb[i], Peca.BRANCO, imgBrancas.get(tipo[i]), tabuleiro))
                .collect(Collectors.toList());

        tabuleiro.addAll(pecaPreta);
        tabuleiro.addAll(pecaBranca);
        /**
         Teste PecaCapturada
         PecaCapturada pecaCapturada;
         for(PecaDesenho p:pecaPreta){
         pecaCapturada = new PecaCapturada(p.x, p.y, p.cor, p.getImageIcon());
         tabuleiro.listaCapturaBranca.add(pecaCapturada);
         }
         for(PecaDesenho p:pecaBranca){
         pecaCapturada = new PecaCapturada(p.x, p.y, p.cor, p.getImageIcon());
         tabuleiro.listaCapturaPreta.add(pecaCapturada);
         }
         */
    }

    private MouseAdapter myMouseListenner() {
        return new MouseAdapter() {

            @Override
            public void mouseDragged(MouseEvent e) {
                if (pecaSelecionada == null || pecaSelecionada.isVazia()) return;
                pecaSelecionada.coorParaDragAndDrop(e.getX(), e.getY());
                e.getComponent().repaint();

            }

            @Override
            public void mousePressed(MouseEvent e) {
                int x = ((e.getX() - dx * 3) / square);
                int y = ((e.getY() - dx) / square);

                if (e.getX() < dx || e.getY() < dx)
                    return;
                if (x > 8 || y > 8)
                    return;

                pecaSelecionada = tabuleiro.getItem(x, y);

                if (pecaSelecionada.cor == tabuleiro.vez) {
                    tabuleiro.setPecaDragAndDrop(pecaSelecionada);
                    e.getComponent().repaint();
                } else {
                    pecaSelecionada = pecaVazia;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (pecaSelecionada == null || pecaSelecionada.isVazia()) return;

                int x = ((e.getX() - dx * 3) / square);
                int y = ((e.getY() - dx) / square);
                var destino = new Point(x, y);

                Status k = pecaSelecionada.podeMoverAte(destino);
                tabuleiro.lastStatus =
                        "Peca: " + pecaSelecionada.getClass().getSimpleName()
                                + " (" + pecaSelecionada.x + "," + pecaSelecionada.y + ") -> "
                                + tabuleiro.getItem(destino.x, destino.y).getClass().getSimpleName() + " ("
                                + destino.x + "," + destino.y + ") Status: " + k + "";
                e.getComponent().repaint();
                //+ " COR: " + pecaSelecionada.cor
                while (k != Status.NADA) {
                    k = metodo[k.ordinal()].execute(pecaSelecionada, destino);

                }
                tabuleiro.sort(Comparator.comparingDouble(PecaDesenho::getY));
                tabuleiro.setPecaDragAndDrop(pecaVazia);

                e.getComponent().repaint();

                if (pecaSelecionada instanceof Peao) {

                    boolean promover = pecaSelecionada.y == (pecaSelecionada.cor == Peca.BRANCO ? 0 : 7);
                    if (promover) {
                        tabuleiro.lastStatus += " " + k;
                        metodo[Status.PROMOCAO.ordinal()].execute(pecaSelecionada, destino);
                        e.getComponent().repaint();
                    }
                }
            }
        };
    }
}

@FunctionalInterface
interface Metodo {
    Status execute(PecaDesenho origem, Point destino);
}