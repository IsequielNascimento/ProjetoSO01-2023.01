package gui.resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class CestoForm extends JPanel {

    private static final int TAMANHO_TXT = 12;

    //Painel form
    protected JPanel formPanel;

    protected JPanel buttonPanel;

    protected JButton btnCesto;


    protected JLabel lblQuantidadeCesto;
    protected JTextField textFieldQuantidadeCesto;


    public CestoForm() {
        this.inicializar();
        this.eventos();
    }



    protected abstract void btnCriarCestoCLick(ActionEvent event);

    private void eventos() {
        btnCesto.addActionListener(this::btnCriarCestoCLick);

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
            textFieldQuantidadeCesto = new JTextField(TAMANHO_TXT);

            formPanel.add(lblQuantidadeCesto);
            formPanel.add(textFieldQuantidadeCesto);

        }
        return formPanel;
    }

    public JPanel getButtonPanel() {

        if (buttonPanel == null) {
            buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            btnCesto = new JButton("Criar cesto");


            buttonPanel.add(btnCesto);

        }
        return buttonPanel;
    }

}
