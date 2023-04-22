package gui.resources;
// javax.swing is a package that contains classes for creating graphical user interfaces
import javax.swing.*;

//JFrame is a class that represents a window
public abstract class CriancaForm extends JFrame{

    public CriancaForm(){
        this.inicializar();
    }

    //inicializar is a method that initializes the window
    private void inicializar(){
        this.setTitle("Criança");
        this.setSize(400, 400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}
