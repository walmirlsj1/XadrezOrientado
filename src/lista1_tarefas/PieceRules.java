package lista1_tarefas;

public class PieceRules extends Piece {
    public int square;

    @Override
    public void move(int x, int y){
        super.move(x, y);
        square = (x+y)%2;
    }
}
