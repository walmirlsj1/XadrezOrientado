package pecas;

import tabuleiro.TabuleiroDesenho;

import javax.swing.*;
import java.awt.*;


public class PecaDesenho extends PecaRegra implements Icon {
    private final ImageIcon img;
    private Point frame;


    public PecaDesenho(int xcol, int ylin, int cor, ImageIcon img) {
        super(xcol, ylin, cor);
        this.img = img;
        frame = new Point();

    }

    public PecaDesenho(int xcol, int ylin, int cor, ImageIcon img, TabuleiroDesenho tabuleiro) {
        this(xcol, ylin, cor, img);
        this.tabuleiro = tabuleiro;

        coordFixaNoTabuleiro();
    }

    public void coordFixaNoTabuleiro() {
        var tab = tabuleiro;
        var w = (img.getIconWidth() - tab.square) / 2;
        var h = (img.getIconHeight() - tab.square) + (tab.square / 5);

        frame.x = (int) (this.getX() * tab.square - w + tab.dx*3);
        frame.y = (int) (this.getY() * tab.square - h + tab.dx);
        dragdrop = false;
    }

    public void coorParaDragAndDrop(int x, int y) {
        var w = img.getIconWidth() / 2;
        var h = img.getIconHeight() * 2 / 3;
        frame.x = x - w;
        frame.y = y - h;
        dragdrop = true;

    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        img.paintIcon(c, g, frame.x, frame.y);
    }

    @Override
    public int getIconWidth() {
        return img.getIconWidth();
    }

    @Override
    public int getIconHeight() {
        return img.getIconHeight();
    }

    public ImageIcon getImageIcon(){
        return img;
    }
}
