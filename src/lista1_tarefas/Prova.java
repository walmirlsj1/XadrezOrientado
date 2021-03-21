package lista1_tarefas;

import pecas.*;
import regras.Status;
import tabuleiro.TabuleiroDesenho;

import javax.swing.*;
import javax.swing.text.TabableView;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Prova {
    public static List<Point> getPointsEntre(Point origem, Point destino) {
        int dx = destino.x - origem.x;
        int dy = destino.y - origem.y;
        if (dx != 0) dx /= Math.abs(dx);
        if (dy != 0) dy /= Math.abs(dy);

        var p = new Point(origem.x, origem.y);
        p.translate(dx, dy);
        var list = new Vector<Point>();
        while (!destino.equals(p)) {
            list.add(new Point(p));
            p.translate(dx, dy);

        }
        return list;
    }

    public List<ImageIcon> getImageW(){
        return Arrays.stream("PRNBQK".split(""))
                .map(n ->String.format("w%s.png", n))
                .map(file -> getClass().getResource(String.format("/%s", file)))
                .map(url -> new ImageIcon(url))
                .collect(Collectors.toList());
    }
    public List<ImageIcon> getImageB(){
        return Arrays.stream("PRNBQK".split(""))
                .map(n ->String.format("b%s.png", n))
                .map(file -> getClass().getResource(String.format("/%s", file)))
                .map(url -> new ImageIcon(url))
                .collect(Collectors.toList());
    }
    public HashMap<String,ImageIcon> getImages(){
        HashMap hash = new HashMap<String, ImageIcon>();
        var b = this.getImageB();
        var w = this.getImageW();

        b.forEach(a -> hash.put(a.getDescription().substring(a.getDescription().length()-6, a.getDescription().length()-4), a));
        w.forEach(a -> hash.put(a.getDescription().substring(a.getDescription().length()-6, a.getDescription().length()-4), a));
        return hash;
    }

    public static void print(String[] vetor){
        Arrays.asList(vetor).forEach(
                s->{
                    System.out.println("b"+s);
                    System.out.println("w"+s);
                }
        );

//        var saida = lista.stream()
//                .filter(s -> s.toLowerCase().charAt(s.length()-1) == 'a')
//                .sorted(String::compareToIgnoreCase)
//                .collect(Collectors.toList());
//        saida.forEach(System.out::println);
//
//        var teste = IntStream.range(0, vetor.length*2)
//                .mapToObj(i -> vetor[i%vetor.length])
//                .sorted(String::compareToIgnoreCase)
//                .collect(Collectors.toList());
//        IntStream.range(0, teste.size())
//                .mapToObj(i -> p[i % 2]+ teste.get(i))
//                .forEach(System.out::println);
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        var texto = sc.next();
        System.out.println(new Prova().getImages());
//        new Prova().getImages();.
//        var origem = new Point(0,0);
//        var destino = new Point(0,0);

//        int cont = 0;
//        var lista = getPointsEntre(origem, destino);
//        for (Point ponto: lista){
//            if(todaLista.indexOf(ponto)) cont++;
//        }
//        if (cont > 0)
//            throw new PercursoObstruidoException();
    }



    //    public static void main(String[] args) {
//        System.out.println(verifica("a7,b5,c3,d1,e6,f8,g2,h4".split(",")));
//        System.out.println(verifica("a2,b4,c4,d8,e3,f1,g7,h5".split(",")));
//    }
    public static final int WHITE=1, BLACK=0, NO_COLOR=-1;

//    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        var texto = sc.next();
//
//
//        char input;
//        String[] texto1 = texto.split("/");
//
//        String saida = "";
//
//        saida = "+---+---+---+---+---+---+---+---+\n|";
//        for (int y = 0; y < texto1.length; y++){
//            for (int x = 0; x < texto1[y].length();x++) {
//                input = texto1[y].charAt(x);
//                if (Character.isDigit(input)) {
//                    for(int k = 0; k<Integer.valueOf(input + "");k++)
//                        saida += " . |";
//                } else {
//                    saida += " " + input + " |";
//                }
//            }
//            saida += "\n+---+---+---+---+---+---+---+---+\n|";
//        }
//        saida = saida.substring(0, saida.length()-2);
//        System.out.println(saida);
//    }


    public static boolean verifica(String[] entrada) {
        /**
         * a = 1
         * b = 2
         * c = 3
         * d = 4
         * e = 5
         * f = 6
         * g = 7
         * h = 8
         */
//        String[][] tabuleiro = {
//                {"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8"},
//                {"a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7"},
//                {"a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6"},
//                {"a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5"},
//                {"a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4"},
//                {"a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3"},
//                {"a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2"},
//                {"a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1"},
//        };
        int x = -999, y = -999;
        int x1 = -999, y1 = -999;
        int zx, zy;

        for (String e : entrada) {
            switch (e.charAt(0)) {
                case 'a':
                    x = 0;
                    break;
                case 'b':
                    x = 1;
                    break;
                case 'c':
                    x = 2;
                    break;
                case 'd':
                    x = 3;
                    break;
                case 'e':
                    x = 4;
                    break;
                case 'f':
                    x = 5;
                    break;
                case 'g':
                    x = 6;
                    break;
                case 'h':
                    x = 7;
                    break;
            }
            y = Integer.valueOf(e.charAt(1) + "") - 1;

            for (String teste : entrada) {
                // verificar se é ortogonal, ou diagonal
                if (!teste.equals(e)) {
                    switch (teste.charAt(0)) {
                        case 'a':
                            x1 = 0;
                            break;
                        case 'b':
                            x1 = 1;
                            break;
                        case 'c':
                            x1 = 2;
                            break;
                        case 'd':
                            x1 = 3;
                            break;
                        case 'e':
                            x1 = 4;
                            break;
                        case 'f':
                            x1 = 5;
                            break;
                        case 'g':
                            x1 = 6;
                            break;
                        case 'h':
                            x1 = 7;
                            break;
                    }
                    y1 = Integer.valueOf(teste.charAt(1) + "") - 1;

                    if (e.charAt(0) == teste.charAt(0)) return false;

                    if (e.charAt(1) == teste.charAt(1)) return false;


                    // DIAGONAL

                    for (zy = y + 1, zx = x + 1; zy < 8 && zx < 8; zy++, zx++) {
                        if (y1 == zy && zx == x1) return false;
                    }

                    for (zy = y + 1, zx = x - 1; zy < 8 && zx > -1; zy++, zx--) {
                        if (y1 == zy && zx == x1) return false;
                    }

                    for (zy = y - 1, zx = x + 1; zy > -1 && zx < 8; zy--, zx++) {
                        if (y1 == zy && zx == x1) return false;
                    }

                    for (zy = y - 1, zx = x - 1; zy > -1 && zx > -1; zy--, zx--) {
                        if (y1 == zy && zx == x1) return false;
                    }
                }
            }

        }
        return true;
    }
}
