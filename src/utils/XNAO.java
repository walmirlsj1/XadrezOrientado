package utils;

import pecas.*;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class XNAO extends JFrame {

    public XNAO() {
        setLayout(new GridLayout(2, 8));
        String[] nome = {"peao", "torre", "cavalo", "bispo", "rainha", "rei"};
        var imgPretas = Arrays.stream(nome)
                .map(str -> "/img/" + str + "-preto.png")
                .map(str -> getClass().getResource(str))
                .map(urlx -> new ImageIcon(urlx))
                .collect(Collectors.toList());

        Class[] vet = {Peao.class, Torre.class, Cavalo.class, Bispo.class, Rainha.class, Rei.class};
        int[] qtd = {0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 3, 2, 1};
        var pecaPreta = Arrays.stream(qtd)
                .mapToObj(i -> (PecaDesenho) Factory2222.criar(vet[i], 0, 0, Peca.PRETO, imgPretas.get(i)))
                .collect(Collectors.toList());

        pecaPreta.forEach(p -> this.add(new JLabel(p)));


//        Bispo bpe = new Bispo(2,0, Peca.PRETO, imgPretas.get(3));
//        Bispo bpd = new Bispo(5,0, Peca.PRETO, imgPretas.get(3));
//
//        Cavalo cpe = new Cavalo(1,0, Peca.PRETO, imgPretas.get(2));
//        Cavalo cpd = new Cavalo(6,0, Peca.PRETO, imgPretas.get(2));
//
//        Torre tpe = new Torre(0,0, Peca.PRETO, imgPretas.get(1));
//        Torre tpd = new Torre(7,0, Peca.PRETO, imgPretas.get(1));
//
//        //TESTE DE DESENHO
//        add( new JLabel(tpe) );
//        add( new JLabel(cpe) );
//        add( new JLabel(bpe) );
//        add( new JLabel(bpd) );
//        add( new JLabel(cpd) );
//        add( new JLabel(tpd) );
//        pack();

    }

    public static void main(String[] args) {
        new XNAO().setVisible(true);
    }
}


class Factory2222 {

    public static Object criar(Class classe, Object... param) {
        try {
            Constructor[] vetor = classe.getConstructors();
            Constructor c = Arrays.stream(vetor)
                    .filter(constructor -> constructor.getParameterCount() == param.length)
                    .findFirst().get();
            return c.newInstance(param);

        } catch (Exception e) {
            return null;
        }
    }
}