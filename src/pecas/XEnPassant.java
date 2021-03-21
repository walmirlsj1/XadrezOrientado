package pecas;

import java.awt.*;

public class XEnPassant extends PecaDesenho {

    public XEnPassant(PecaDesenho origem, Point destino) {
        super(-9, -9, origem.cor, null, origem.tabuleiro);
        // calcular local da peca en_passant da origem pro destino...
        int dx = destino.x - origem.x;
        int dy = destino.y - origem.y;
        if (dx != 0) dx /= Math.abs(dx);
        if (dy != 0) dy /= Math.abs(dy);

        var p = new Point(origem.x, origem.y);
        p.translate(dx, dy);

        this.x = p.x;
        this.y = p.y;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        //ignora pintura do XEnPassant
        //    g.translate(tabuleiro.dx, tabuleiro.dx);
        //    g.setColor(Color.PINK);
        //    g.fillRect(this.x * tabuleiro.square, this.y * tabuleiro.square, tabuleiro.square, tabuleiro.square);
        //    g.translate(-tabuleiro.dx, -tabuleiro.dx);
    }

    @Override
    public void coordFixaNoTabuleiro() {
    }

    @Override
    public void coorParaDragAndDrop(int x, int y) {
    }
}
