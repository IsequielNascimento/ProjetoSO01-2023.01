import javax.swing.*;
import java.awt.*;

public class CriarCriancaPanel extends JPanel {


    private JPanel formPanel;
    private JPanel buttonPanel;
    protected static JButton buttonCriarThread;
    protected JButton btnBrincar;
    //Nome
    protected static JLabel textFieldNomeDaCrianca;
    private static final int TAMANHO_TXT = 16;
    protected static JTextField nomeTextField;
    //Bola?
    protected JLabel lblBola;
    protected static JCheckBox checkBoxBola;
    //Tempo de brincadeira
    protected JLabel lblTempoBrincadeira;
    protected static JTextField textFieldTempoBrincadeira;
    protected JLabel lblTempoQuieta;
    protected static JTextField textFieldTempoQuieta;

    public CriarCriancaPanel() {
        this.inicializar();

    }

    private void inicializar() {
        this.setLayout(new BorderLayout());
        this.add(this.criarCriancaPanel(), BorderLayout.CENTER);
        this.add(this.getButtonPanel(), BorderLayout.PAGE_END);

    }

    public JPanel criarCriancaPanel() {
        if (formPanel == null) {
            formPanel = new JPanel(new GridLayout(4, 2));

            textFieldNomeDaCrianca = new JLabel("Nome");
            textFieldNomeDaCrianca.setHorizontalAlignment(JLabel.CENTER);
            nomeTextField = new JTextField(TAMANHO_TXT);

            lblBola = new JLabel("Tem Bola?");
            lblBola.setHorizontalAlignment(JLabel.CENTER);
            checkBoxBola = new JCheckBox();

            lblTempoBrincadeira = new JLabel("Tempo de Brincadeira (em segundos");
            lblTempoBrincadeira.setHorizontalAlignment(JLabel.CENTER);
            textFieldTempoBrincadeira = new JTextField(TAMANHO_TXT);

            lblTempoQuieta = new JLabel("Tempo Quieto (em segundos):");
            lblTempoQuieta.setHorizontalAlignment(JLabel.CENTER);
            textFieldTempoQuieta = new JTextField(TAMANHO_TXT);

            formPanel.add(textFieldNomeDaCrianca);
            formPanel.add(nomeTextField);

            formPanel.add(lblBola);
            formPanel.add(checkBoxBola);

            formPanel.add(lblTempoBrincadeira);
            formPanel.add(textFieldTempoBrincadeira);

            formPanel.add(lblTempoQuieta);
            formPanel.add(textFieldTempoQuieta);

        }
        return formPanel;
    }
    public JPanel getButtonPanel() {
        //If the button panel is null, create it
        if (buttonPanel == null) {
            buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            //Create the buttons
            buttonCriarThread = new JButton("Criar Criança");



            //Add the buttons to the button panel
            buttonPanel.add(buttonCriarThread);


        }
        return buttonPanel;

    }
}
