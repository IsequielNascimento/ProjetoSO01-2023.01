package gui.resources;
import threads.Cesto;
import threads.Semaforo;
import threads.Crianca;
import java.awt.event.ActionEvent;
import java.util.concurrent.Semaphore;

public class CestoConcrete extends CestoForm{

        @Override
        public void btnCriarCestoCLick(ActionEvent event) {
            Cesto.K = Integer.parseInt(textFieldQuantidadeCesto.getText());
            Cesto.cesto = new Semaphore(Cesto.K);
        }

        @Override
        public void btnBrincarCLick(ActionEvent event) {
            // TODO Auto-generated method stub

        }

    @Override
    protected void btnCestoCLick(ActionEvent event) {

    }
}
