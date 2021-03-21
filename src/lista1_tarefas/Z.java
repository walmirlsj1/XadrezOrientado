package lista1_tarefas;

import java.awt.Point;
import java.util.Arrays;

public class Z extends PecaEx {

    /**
     *
     */
    private static final long serialVersionUID = 7037006185971235714L;

    public Z(int xcol, int ylin, int cor) {
        super(xcol, ylin, cor);
//		listMove = new Point[] { new Point(-1, -1), new Point(1, -1), new Point(-1, 1), new Point(1, 1) };
        listMove = Arrays.asList(new Point(-1, -1), new Point(1, -1), new Point(-1, 1), new Point(1, 1));
    }

}
