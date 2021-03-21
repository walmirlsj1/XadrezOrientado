package aula.lambda;

import java.util.Arrays;

public class Capitulo02 {
    public static void main(String[] args) {
        var user1 = new Usuario("Paulo Silveira", 150);
        var user2 = new Usuario("Rodrigo Turini", 120);
        var user3 = new Usuario("Guilherme Silveira", 190);
        var list = Arrays.asList(user1, user2, user3);
        System.out.println(list);
    }
}
