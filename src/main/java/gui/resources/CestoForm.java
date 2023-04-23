package gui.resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class CestoForm extends JFrame {

    private static final int TAMANHO_TXT = 16;

    //Painel form
    protected JPanel formPanel;

    protected  JPanel buttonPanel;

    protected JButton btnCesto;

    protected JButton btnBrincar;

    protected JLabel lblQuantidadeCesto;
    protected JTextField textFieldQuantidadeCesto;


    public CestoForm(){

        this.inicializar();
        this.eventos();
    }

    private void inicializar(){
        this.setTitle("Cesto");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        if(!this.isVisible()) {
            this.setVisible(true);
        }
        this.setResizable(false);

        this.getContentPane().setLayout(new BorderLayout());

        this.getContentPane().add(this.getFormPanel(), BorderLayout.CENTER);
        this.getContentPane().add(this.getButtonPanel(), BorderLayout.PAGE_END);

        this.pack();
    }

    protected abstract void btnCriarCestoCLick(ActionEvent event);
    protected abstract void btnBrincarCLick(ActionEvent event);
    private void eventos(){
        btnCesto.addActionListener(this::btnCriarCestoCLick);
        btnBrincar.addActionListener(this::btnBrincarCLick);
    }

    public JPanel getFormPanel() {

        if (formPanel == null) {
            formPanel = new JPanel( new GridLayout(1, 2));

            lblQuantidadeCesto = new JLabel("Quantidade de bolas no cesto: ");
            textFieldQuantidadeCesto = new JTextField(TAMANHO_TXT);

            formPanel.add(lblQuantidadeCesto);
            formPanel.add(textFieldQuantidadeCesto);

        }
        return formPanel;
    }

    public JPanel getButtonPanel() {

        if (buttonPanel == null) {
            buttonPanel = new JPanel( new FlowLayout(FlowLayout.CENTER));

            btnCesto = new JButton("Criar cesto");
            btnBrincar = new JButton("Brincar");

            buttonPanel.add(btnCesto);
            buttonPanel.add(btnBrincar);
        }
        return buttonPanel;
    }

    protected abstract void btnCestoCLick(ActionEvent event);
}
