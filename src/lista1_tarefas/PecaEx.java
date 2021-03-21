package lista1_tarefas;

import java.awt.Point;
import java.util.List;
import java.util.stream.Collectors;

import pecas.Peca;


public class PecaEx extends Point {
    public static int BRANCA = 0, PRETA = 1, VAZIA = 2; // ja sao static e final
    int cor;
    //	protected Point[] listMove;
    protected List<Point> listMove;

    public PecaEx(int x, int y, int cor) {
        super(x, y);
        this.cor = cor;
        // TODO Auto-generated constructor stub
    }

    public int getCor() {
        return cor;
    }

    public boolean isVazio() {
        return cor == PecaEx.VAZIA;
    }

    //	public Point[] getMove() {
    public List<Point> getMove() {
//		Point[] ret = new Point[listMove.length];
//		for (int i = 0; i < listMove.length; i++) {
//			ret[i] = new Point(x, y);
//			ret[i].translate(listMove[i].x, listMove[i].y);
//		}
//		return ret;
        return listMove.stream()
                .map(point -> new Point(x + point.x, y + point.y))
                .collect(Collectors.toList());
//		return listMove.stream()
//				.map(p -> p.x + p.y)
//				.collect(Collectors.toList());
    }

}
