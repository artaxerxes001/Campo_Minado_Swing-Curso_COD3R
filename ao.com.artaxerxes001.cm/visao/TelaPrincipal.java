package ao.com.artaxerxes001.cm.visao;

import ao.com.artaxerxes001.cm.modelo.Tabuleiro;

import javax.swing.*;

public class TelaPrincipal extends JFrame {
    public TelaPrincipal() {
        Tabuleiro tabuleiro = new Tabuleiro(16, 30, 20);
        add(new PanelTabuleiro(tabuleiro));
        setTitle("Campo Minado");

        setSize(690, 438);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TelaPrincipal();
    }
}
