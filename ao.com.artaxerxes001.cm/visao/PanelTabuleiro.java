package ao.com.artaxerxes001.cm.visao;

import ao.com.artaxerxes001.cm.modelo.Tabuleiro;

import javax.swing.*;
import java.awt.*;

public class PanelTabuleiro extends JPanel {

    public PanelTabuleiro(Tabuleiro tabuleiro) {
        setLayout(new GridLayout(tabuleiro.getLinhas(), tabuleiro.getColunas()));

        int total = tabuleiro.getLinhas() * tabuleiro.getColunas();
        tabuleiro.paraCadaCampo(c -> add(new BotaoCampo(c)));

        tabuleiro.registrarObservador(e -> {
//             SwingUtilities.invokeLater permite atualizar a interface e depois executa a ação que foi implentada dentro do seu bloco de codigo
            SwingUtilities.invokeLater(() -> {

                if (e.isGanhou()){
                    JOptionPane.showMessageDialog(this, "Ganhou :)");
                }else{
                    JOptionPane.showMessageDialog(this, "Perdeu :(");

                }
                tabuleiro.reiniciar();
            });
        });
    }
}
