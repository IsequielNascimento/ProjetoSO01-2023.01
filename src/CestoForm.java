import javax.swing.*;
import java.awt.*;

public class CestoForm extends JPanel {

    private static final int TAMANHO_TXT = 12;

    //Painel form
    protected JPanel formPanel;

    protected JPanel buttonPanel;

    protected static JButton btnCriarCesto;


    protected JLabel lblQuantidadeCesto;
    protected static JTextField textFieldQuantidadeCesto;


    public CestoForm() {
        this.inicializar();

    }

    private void inicializar() {
        this.setLayout(new BorderLayout());
        this.add(this.getFormPanel(), BorderLayout.CENTER);
        this.add(this.getButtonPanel(), BorderLayout.PAGE_END);


    }
    public JPanel getFormPanel() {

        if (formPanel == null) {
            formPanel = new JPanel(new GridLayout(1, 2));

            lblQuantidadeCesto = new JLabel("Quantidade de bolas no cesto: ");
            lblQuantidadeCesto.setHorizontalAlignment(JLabel.RIGHT);
            textFieldQuantidadeCesto = new JTextField(TAMANHO_TXT);
            textFieldQuantidadeCesto.setMaximumSize(new Dimension(80, 20));

            formPanel.add(lblQuantidadeCesto);
            formPanel.add(textFieldQuantidadeCesto);

        }
        return formPanel;
    }

    public JPanel getButtonPanel() {

        if (buttonPanel == null) {
            buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            btnCriarCesto = new JButton("Criar cesto");


            buttonPanel.add(btnCriarCesto);

        }
        return buttonPanel;
    }

}
