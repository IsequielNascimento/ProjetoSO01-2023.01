package gui.resources;
// javax.swing is a package that contains classes for creating graphical user interfaces
import javax.swing.*;
import java.awt.*;

//JFrame is a class that represents a window
public abstract class CriancaForm extends JFrame{

    private static final int TAMANHO_TXT = 16;

    //Painel form
    protected JPanel formPanel;

    //Painel botões
    protected JPanel buttonPanel;

    //Botão Criar Criança
    protected JButton btnCriarCrianca;
    protected JButton btnCesto;
    protected JButton btnBrincar;


    //Nome
    protected JLabel lblNome;
    protected JTextField nomeTextField;
    //Bola?
    protected JLabel lblBola;
    protected JCheckBox checkBoxBola;
    //Tempo de brincadeira
    protected JLabel lblTempoBrincadeira;
    protected JTextField textFieldTempoBrincadeira;
    //Tempo de quieta
    protected JLabel lblTempoQuieta;
    protected JTextField textFieldTempoQuieta;




    //CriancaForm is a class that represents a window
    public CriancaForm(){
        this.inicializar();
    }

    //inicializar is a method that initializes the window
    private void inicializar(){
        this.setTitle("Criança");

        //DISPOSE_ON_CLOSE is a constant that represents the default close operation for a window
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);

        //setResizable is a method that sets whether the window is resizable by the user
        this.setResizable(false);

        //getContentPane is a method that returns the content pane of the window (the area where the components are placed)
        //BorderLayout is a class that represents a layout manager that places components in five regions: north, south, east, west, and center
        this.getContentPane().setLayout(new BorderLayout());


        this.getContentPane().add(this.getFormPanel(), BorderLayout.CENTER);
        this.getContentPane().add(this.getButtonPanel(), BorderLayout.PAGE_END);

        //pack is a method that sizes the window to fit the preferred size and layouts of its subcomponents
        this.pack();
    }

    public JPanel getFormPanel() {
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
            btnCesto = new JButton("Cesto");
            btnBrincar = new JButton("Brincar");

            //Add the buttons to the button panel
            buttonPanel.add(btnCriarCrianca);
            buttonPanel.add(btnCesto);
            buttonPanel.add(btnBrincar);
        }
        return buttonPanel;
    }
}
