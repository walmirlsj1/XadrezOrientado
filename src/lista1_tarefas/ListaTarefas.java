package lista1_tarefas;

import java.awt.Point;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import pecas.*;

public class ListaTarefas {
    private PecaEx[][] tabuleiro88;

    public ListaTarefas() {
//		tabuleiro88 = new DefaultTabuleiro(8, 8);
        tabuleiro88 = new PecaEx[8][8];
        preencherTabuleiro();
    }

    private void preencherTabuleiro() {
        PecaEx peca;
        int cor;
        int peca1;
        for (int lin = 0; lin < 8; lin++) {
            for (int col = 0; col < 8; col++) {
                cor = new Random().nextInt(3);
//				System.out.println(cor);
                peca = new PecaEx(lin, col, cor);

                tabuleiro88[lin][col] = peca;
            }
        }

        for (int lin = 0; lin < 8; lin++) {
            for (int col = 0; col < 8; col++) {
                System.out
                        .println("tabuleiro lin: " + lin + " col: " + col + " cor: " + tabuleiro88[lin][col].getCor());
//				tabuleiro88.setPeca(peca, lin, col);
            }
        }
    }

    public static void main(String[] args) throws NoSuchMethodException {

//		
//		new ListaTarefas();
//
//		Y y = new Y(5, 6, PecaEx.BRANCA);
////		Point[] p = y.getMove();
////		System.out.println(Arrays.toString(p));
//		List<Point> list = y.getMove();
//		System.out.println(list);
//
//		Z z = new Z(5, 6, PecaEx.BRANCA);
//		list = z.getMove();
////		System.out.println(Arrays.toString(p));
//		System.out.println(list);
//
////		exercicio 4
//		var list2 = list.stream().map(p -> new PecaEx(p.x, p.y, PecaEx.VAZIA)).collect(Collectors.toList());
//		System.out.println(list2);
//
//		// exercicio 5
////		var cont = list2.stream().filter(p -> p.cor == Peca.VAZIO)
////				.count();
//
//		var cont = list2.stream().filter(PecaEx::isVazio).count();
//		System.out.println(cont);
//
//		System.out.println("***********************");
//
//		// exercicio 6
//		var lista = new ArrayList<Point>();
//		lista.add(new Point(2, 2));
//		lista.add(new Point(2, 2));
//		lista.add(new Point(1, 2));
//		lista.add(new Point(1, 2));
//		lista.add(new Point(3, 4));
//		lista.add(new Point(4, 5));
//
//		// exercicio 6
//		var branco1 = lista.stream().distinct().collect(Collectors.toList());
//		System.out.println(branco1);
//		// exercicio 7
//		var branco = list2.stream().anyMatch(peca -> peca.cor == PecaEx.BRANCA);
//		System.out.println(branco);
//
//		var ordenado = branco1.stream().sorted(Comparator.comparingDouble(Point::getX)).collect(Collectors.toList());
////		.sort().collect(Collectors.toList());
//		System.out.println(ordenado);
////		list2.sort((o1, o2) -> );
////		list2.removeIf(peca -> peca.cor == PecaEx.VAZIA);
//		list2.removeIf(peca -> peca instanceof PecaEx);
//		
////		var vet = {P.class, T.class, N.class, B.class, Q.class, K.class};
//		try {
//		// returns the Constructor object of the public constructor
//		Class cls[] = new Class[] { Bispo.class };
//		// Constructor c = cls[0].getClass().getConstructor();
////	         Peca p = (Peca) .getConstructor().newInstance(10,10,1);
////	         System.out.println(p.getX());
////	         Class<MyClass> c = MyClass.class;
//		Constructor<Peca> cons;
//		cons = cls[0].getConstructor(Integer.class, Integer.class, Integer.class);

//	      } catch(Exception e) {
//	         System.out.println(e);
//	      } 

        int si, sf = -1;
        si = 7;
        int c = 0;

        while (si != 999) {

            switch (si) {
                case 2:
                    sf = 0;
                    break;
                case 3:
                    sf = 2;
                    break;
                case 5:
                    sf = 2;
                    break;
                case 6:
                    sf = 2;
                    break;
                case 7:
                    sf = 3;
                    break;
                case 0:
                    sf = 999;
                    break;
            }

            System.out.println("(Etapas " + ++c + ") #### SI: " + si + " -> SF: " + sf);

            si = sf;

        }

    }

}
