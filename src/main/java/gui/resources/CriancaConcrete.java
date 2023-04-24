package gui.resources;
import gui.resources.animacao.CriancaAnimacao;
import threads.Cesto;
import threads.Crianca;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;



public class CriancaConcrete extends CriancaForm{

    int interfaceCesto = 1;
    public int criancaComBola = 1;
    public int criancaSemBola = 2;
    public int totalCriancas =0;
    int interfaceAnimacao = 1;
    // Lista de crianças que será usada para iniciar a brincadeira
    public List<Crianca> criancas = new ArrayList<Crianca>();



    // Construtor da classe CriancaConcrete que recebe como parâmetro o nome da criança e se ela tem bola ou não (true ou false) e o tempo de brincadeira e de quieta da criança
    @Override
    public void btnCriarCriancaCLick(ActionEvent event) {

       criancas.add(new Crianca(nomeTextField.getText(), checkBoxBola.isSelected(), Integer.parseInt(textFieldTempoBrincadeira.getText()), Integer.parseInt(textFieldTempoQuieta.getText())));
       if (checkBoxBola.isSelected()) {
           criancaComBola++;
           totalCriancas++;
       }
       else {
           criancaSemBola++;
           totalCriancas++;
       }
        System.out.println("Criança criada");
        System.out.println("Nome: " + nomeTextField.getText());
        System.out.println("Tem bola? " + checkBoxBola.isSelected());
        System.out.println("Tempo de brincadeira: " + textFieldTempoBrincadeira.getText());
        System.out.println("Tempo de quieta: " + textFieldTempoQuieta.getText());

        //System.out.println(Crianca.totalCrianca);
    }

    
    @Override
    protected void btnCestoCLick(ActionEvent event) {
       if (interfaceCesto == 1){
           new CestoConcrete();
           interfaceCesto --;
       }
       else {
           System.out.println("Já existe uma interface de cesto");
       }

    }

    // Método que inicia a brincadeira das crianças
    @Override
    protected void btnBrincarCLick(ActionEvent event) {
        if (interfaceAnimacao == 1){
          new CriancaAnimacao();
            interfaceAnimacao --;

            // Inicia a brincadeira das crianças
            Crianca.criancas = criancas;
            Crianca.criancas.forEach(Crianca::start);
        }
        else {
            System.out.println("Já existe uma interface de animação");
        }
        if (criancas.size() == 0){
            System.out.println("Não há crianças para brincar");
        }
        else {
            System.out.println("Brincando");
        }
        if (Cesto.K == 0){
            System.out.println("Não foi definido a capacidade do cesto");
        }
        else {
            System.out.println("A capacidade do cesto é de: " + Cesto.K);
        }


    }
}
