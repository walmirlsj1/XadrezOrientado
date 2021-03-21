package lista1_tarefas;

import javax.swing.*;

public class PieceIcon extends PieceRules {
    public JLabel icon;

    @Override
    public void move(int x, int y){
        super.move(x, y);
        icon.setLocation(50*x, 50*y);
    }
}