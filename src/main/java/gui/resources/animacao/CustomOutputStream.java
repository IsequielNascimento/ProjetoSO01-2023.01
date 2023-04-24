package gui.resources.animacao;
import java.io.OutputStream;
import java.io.PrintStream;
import javax.swing.JTextArea;

public class CustomOutputStream extends PrintStream {
    private JTextArea textArea;

    public CustomOutputStream(OutputStream out, JTextArea textArea) {
        super(out);
        this.textArea = textArea;
    }

    @Override
    public void write(byte[] b, int off, int len) {
        String s = new String(b, off, len);
        textArea.append(s);
    }
}
