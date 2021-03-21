package lista1_tarefas;

import java.awt.*;

public class Piece extends Point {
    public int moves = 0;

    @Override
    public void move(int x, int y){
        super.move(x, y);
        moves++;
    }
}
