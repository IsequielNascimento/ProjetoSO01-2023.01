
import javax.swing.*;
import java.awt.*;
import java.io.PrintStream;

public class LogCrianca extends JPanel {
    private JTextArea textArea;


    public LogCrianca() {
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(500, 200));

        // Cria JTextArea e adiciona ao JScrollPane
        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Adiciona JScrollPane ao painel
        add(scrollPane, BorderLayout.CENTER);
        // Cria um CustomOutputStream que escreve na JTextArea
        CustomOutputStream outputStream = new CustomOutputStream(System.out, textArea);

        // Redireciona a saída padrão e de erro para o CustomOutputStream
        System.setOut(new PrintStream(outputStream, true));
        System.setErr(new PrintStream(outputStream, true));
    }



    public void log(String mensagem) {
        // Adiciona mensagem ao JTextArea
        textArea.append(mensagem + "\n");

        // Move o cursor do JTextArea para a última linha
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
