package gui.resources.animacao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class CriarCriancaPanel extends JPanel {

    private JPanel formPanel;
    private JPanel buttonPanel;
    protected JButton btnCriarCrianca;
    protected JLabel lblNome;
    private static final int TAMANHO_TXT = 16;
    protected JTextField nomeTextField;
    //Bola?
    protected JLabel lblBola;
    protected JCheckBox checkBoxBola;
    //Tempo de brincadeira
    protected JLabel lblTempoBrincadeira;
    protected JTextField textFieldTempoBrincadeira;
    protected JLabel lblTempoQuieta;
    protected JTextField textFieldTempoQuieta;

    public CriarCriancaPanel(){
        this.inicializar();
        this.eventos();
    }

    protected abstract void btnCriarCriancaCLick(ActionEvent event);

    private void eventos(){
        btnCriarCrianca.addActionListener(this::btnCriarCriancaCLick);
    }
    private void inicializar(){
        this.setLayout(new BorderLayout());
        this.add(this.CriarCriancaPanel(), BorderLayout.CENTER);
        this.add(this.getButtonPanel(), BorderLayout.PAGE_END);

    }
    public JPanel CriarCriancaPanel() {
        if (formPanel == null) {
            formPanel = new JPanel( new GridLayout(4, 2));

            lblNome = new JLabel("Nome");
            nomeTextField = new JTextField( TAMANHO_TXT);

            lblBola = new JLabel("Tem Bola?");
            checkBoxBola = new JCheckBox();

            lblTempoBrincadeira = new JLabel("Tempo de Brincadeira");
            textFieldTempoBrincadeira = new JTextField(TAMANHO_TXT);

            lblTempoQuieta = new JLabel("Tempo de Quieta");
            textFieldTempoQuieta = new JTextField(TAMANHO_TXT);

            formPanel.add(lblNome);
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
            btnCriarCrianca = new JButton("Criar Criança");


            //Add the buttons to the button panel
            buttonPanel.add(btnCriarCrianca);

        }
        return buttonPanel;
    }

}
