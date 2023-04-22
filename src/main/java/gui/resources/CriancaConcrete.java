package gui.resources;
import threads.Crianca;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class CriancaConcrete extends CriancaForm{

    private List<Crianca> criancas = new ArrayList<Crianca>();

    @Override
    public void btnCriarCriancaCLick(ActionEvent event) {
       criancas.add(new Crianca(nomeTextField.getText(), checkBoxBola.isSelected(), Integer.parseInt(textFieldTempoBrincadeira.getText()), Integer.parseInt(textFieldTempoQuieta.getText())));



        System.out.println("Criança criada");
        System.out.println("Nome: " + nomeTextField.getText());
        System.out.println("Tem bola? " + checkBoxBola.isSelected());
        System.out.println("Tempo de brincadeira: " + textFieldTempoBrincadeira.getText());
        System.out.println("Tempo de quieta: " + textFieldTempoQuieta.getText());

        //System.out.println(Crianca.totalCrianca);
    }

    @Override
    protected void btnCestoCLick(ActionEvent event) {

    }

    @Override
    protected void btnBrincarCLick(ActionEvent event) {


        Crianca.criancas = criancas;
        Crianca.criancas.forEach(Crianca::start);


    }
}
