package gui.resources.animacao;

import javax.swing.*;
import java.awt.*;

public class LogCrianca extends JPanel {
    private JTextArea textArea;

    public LogCrianca() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(400, 200));

        // Cria JTextArea e adiciona ao JScrollPane
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Adiciona JScrollPane ao painel
        add(scrollPane, BorderLayout.CENTER);

    }

    public void log(String mensagem) {
        // Adiciona mensagem ao JTextArea
        textArea.append(mensagem + "\n");

        // Move o cursor do JTextArea para a última linha
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
