package pecas;

import javax.swing.*;
import java.awt.*;


public class PecaCapturada extends Peca implements Icon {
    private final ImageIcon img;

    public PecaCapturada(int xcol, int ylin, int cor, ImageIcon img) {
        super(xcol, ylin, cor);
        this.img = img;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        img.paintIcon(c, g, x, y);
    }

    @Override
    public int getIconWidth() {
        return img.getIconWidth();
    }

    @Override
    public int getIconHeight() {
        return img.getIconHeight();
    }

}
