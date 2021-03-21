package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PromocaoPeao {
    private String option;


//    public PromocaoPeao() {
//        super();
//        JFrame frame = new JFrame();
//        frame.setLayout(new GridLayout(5, 1));
//
//        JLabel  lblMsg = new JLabel("Selecione a peça que deseja promover o Peão?");
//        lblMsg.setHorizontalAlignment(JLabel.CENTER);
//        lblMsg.setFont (lblMsg.getFont ().deriveFont (18.0f));
//
//        JButton btnRainha = new JButton("Rainha");
//        btnRainha.addActionListener(getEventosBtn());
//        btnRainha.setFont (btnRainha.getFont ().deriveFont (14.0f));
//
//        JButton btnTorre = new JButton("Torre");
//        btnRainha.addActionListener(getEventosBtn());
//        btnTorre.setFont (btnTorre.getFont ().deriveFont (14.0f));
//
//        JButton btnBispo = new JButton("Bispo");
//        btnRainha.addActionListener(getEventosBtn());
//        btnBispo.setFont (btnBispo.getFont ().deriveFont (14.0f));
//
//        JButton btnCavalo = new JButton("Cavalo");
//        btnRainha.addActionListener(getEventosBtn());
//        btnCavalo.setFont (btnCavalo.getFont ().deriveFont (14.0f));
//
//        frame.add(lblMsg);
//        frame.add(btnRainha);
//        frame.add(btnTorre);
//        frame.add(btnBispo);
//        frame.add(btnCavalo);
//
//        frame.pack();
//        setVisible(true);
//    }

    public static void main(String[] args) {

        Object[] options = {"Rainha", "Torre", "Bispo", "Cavalo"};
        JOptionPane opc = new JOptionPane();
        int n = -1;
        while(n==-1) {
            n = opc.showOptionDialog(
                    null,
                    "Selecione a peça que deseja promover o Peão?",
                    "Promoção do peao",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    -1);
        }
        System.out.println(n);
    }

//    public ActionListener getEventosBtn(){
//        return e -> {
//            Object object = e.getSource();
//            if( object instanceof JButton ){
//                setValue(((JButton) object).getText());
//
//            }
//        };
//
//    }
}
