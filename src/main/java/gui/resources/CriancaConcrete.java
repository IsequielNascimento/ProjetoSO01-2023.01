package gui.resources;
import threads.Crianca;
import threads.Semaforo;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import static threads.Crianca.bolas;
import static threads.Crianca.cesto;


public class CriancaConcrete extends CriancaForm{

    // Lista de crianças que será usada para iniciar a brincadeira
    private List<Crianca> criancas = new ArrayList<Crianca>();



    // Construtor da classe CriancaConcrete que recebe como parâmetro o nome da criança e se ela tem bola ou não (true ou false) e o tempo de brincadeira e de quieta da criança
    @Override
    public void btnCriarCriancaCLick(ActionEvent event) {

       criancas.add(new Crianca(nomeTextField.getText(), checkBoxBola.isSelected(), Integer.parseInt(textFieldTempoBrincadeira.getText()), Integer.parseInt(textFieldTempoQuieta.getText()), cesto, bolas));

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

    // Método que inicia a brincadeira das crianças
    @Override
    protected void btnBrincarCLick(ActionEvent event) {


        // Inicia a brincadeira das crianças
        Crianca.criancas = criancas;
        Crianca.criancas.forEach(Crianca::start);


    }
}
