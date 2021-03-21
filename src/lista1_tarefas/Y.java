package lista1_tarefas;

import java.awt.Point;
import java.util.Arrays;

public class Y extends PecaEx {

    /**
     *
     */
    private static final long serialVersionUID = 7804149189386903309L;

    public Y(int xcol, int ylin, int cor) {
        super(xcol, ylin, cor);
//		listMove = new Point[] { new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1) };
//		listMove = Arrays.asList(new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1));
        listMove = Arrays.asList(new Point(-1, 0), new Point(1, 0), new Point(0, -1), new Point(0, 1));
    }

}
