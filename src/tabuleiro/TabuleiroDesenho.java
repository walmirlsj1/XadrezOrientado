package tabuleiro;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import pecas.Peca;
import pecas.PecaCapturada;
import pecas.Vazia;
import pecas.PecaDesenho;
import regras.Status;

public class TabuleiroDesenho<T extends PecaDesenho> extends TabuleiroRegra<T> implements Icon {
    private Icon icon;
    public int dx, square;
    private HashMap<Status, Color> cor;
    private T pecaSelecionada; // drag drop
    boolean dragdrop = false;

    public TabuleiroDesenho(int casaPx, int margemPx, Image background) {
        super(8, 8);
        dx = margemPx;
        square = casaPx;

        icon = new ImageIcon(criarQuadrados(background));
        pecaSelecionada = (T) new Vazia();
        criarCores();
    }

    public Dimension getDimension() {
        return new Dimension(icon.getIconWidth(), icon.getIconHeight());
    }

    public void setPecaDragAndDrop(T pecaSelecionada) {
        this.pecaSelecionada = (T) pecaSelecionada;
    }


    private void criarCores() {
        cor = new HashMap<Status, Color>();
        cor.put(Status.LIVRE, new Color(69, 152, 80, 255));
        cor.put(Status.PASSO_DUPLO, new Color(1, 64, 14, 255));
        cor.put(Status.ROQUE, new Color(4, 36, 94, 255));
        cor.put(Status.XEQUE, new Color(214, 21, 21, 255));
        cor.put(Status.CAPTURA, new Color(199, 117, 40, 255));
        cor.put(Status.EN_PASSANT, new Color(163, 15, 163, 255));
        cor.put(Status.GUARDA, new Color(0, 187, 255, 255));
        cor.put(Status.AUTO_XEQUE, new Color(54, 11, 11, 255));
        cor.put(Status.IMPEDIDO, new Color(0, 0, 0, 255));
    }

    public BufferedImage criarQuadrados(Image background) {
        int width = nCol * square + 6 * dx;
        int height = nLin * square + 2 * dx;

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = img.getGraphics();
        if (background != null)
            g.drawImage(background, 0, 0, null);

        g.translate(3 * dx, dx);
        Color black = new Color(255, 255, 255, 127);
        Color white = new Color(0, 0, 0, 127);
        for (int lin = 0; lin < nLin; lin++) {
            for (int col = 0; col < nCol; col++) {
                if (((col ^ lin) & 0b1) == 0)
                    g.setColor(white);
                else
                    g.setColor(black);
                g.fillRect(col * square, lin * square, square, square);
            }
        }
        return img;
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        icon.paintIcon(c, g, x, y);
        g.translate(3 * dx, dx);
        try {
            pecaSelecionada.getMovimentosPreCalculados()
                    .forEach(p -> {
                        var px = p.x * square + 5;
                        var py = p.y * square + 5;
                        g.setColor(cor.get(p.status));
                        g.fillRect(px, py, square - 10, square - 10);
                    });
        } catch (Exception e) {
            // no errors
        }
        g.translate(-3 * dx, -dx);

        for (PecaDesenho p : this)
            if (!p.equals(pecaSelecionada))
                p.paintIcon(c, g, p.x * square + 3 * dx, p.y * square + dx - 40);

        this.stream().filter(p -> p.equals(pecaSelecionada)).findFirst()
                .ifPresent(p -> p.paintIcon(c, g, p.x * square + 3 * dx, p.y * square + dx - 40));


        int xCol = 1, yLin = 0;
        for (PecaCapturada p : listaCapturaPreta) {
            p.paintIcon(c, g, xCol * square, yLin * square + dx - 40);
            yLin++;
            if (yLin == 8) {
                yLin = 0;
                xCol--;
            }
        }
        xCol = 8;
        yLin = 0;
        for (PecaCapturada p : listaCapturaBranca) {
            p.paintIcon(c, g, xCol * square + 3 * dx, yLin * square + dx - 40);
            yLin++;
            if (yLin == 8) {
                yLin = 0;
                xCol++;
            }
        }
        g.setColor(new Color(206, 206, 206, 127));
        g.fillRect(dx * 3, 9 * square + dx - 65, this.getIconWidth() - dx * 6, 40);

        g.setColor(Color.BLACK);
        Font gFont = g.getFont().deriveFont(18F);
        g.setFont(gFont);
        g.drawString(lastStatus, dx * 3 + 10, 9 * square + dx - 50);
        g.drawString("Vez: " + (vez == Peca.BRANCO ? "Peças Branca" : "Peças Preta"), dx * 3 + 10, 9 * square + dx - 30);

    }

    @Override
    public int getIconWidth() {
        return icon.getIconWidth();
    }

    @Override
    public int getIconHeight() {
        return icon.getIconHeight();
    }
}
