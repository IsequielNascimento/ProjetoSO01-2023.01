import java.net.URL;
import java.util.concurrent.Semaphore;
import javax.swing.*;

public class Crianca extends Thread {

    private long tempoBrincando;
    private long tempoQuieta;
    private Semaphore cheio;
    private Semaphore vazio;
    private Boolean bola;

    public String getNome() {
        return nome;
    }

    private String nome;

    private JLabelWrapper JLabelWrapper;
    public enum ChildState {
        DESCANSANDO_1,
        DESCANSANDO_2,
        BRINCANDO_1,
        BRINCANDO_2,
        ESPERANDO,
        CESTO_CHEIO,
    }

    public Crianca(String nome, long tempoBrincando, long tempoQuieta, Boolean bola, Semaphore cheio,
                   Semaphore vazio,
                   JLabelWrapper JLabelWrapper
    ) {
        this.tempoBrincando = tempoBrincando * 1000;
        this.tempoQuieta = tempoQuieta * 1000;
        this.cheio = cheio;
        this.vazio = vazio;
        this.bola = bola;
        this.nome = nome;
        this.JLabelWrapper = JLabelWrapper;
    }



    @Override
    public void run() {
        while (true) {
            if (bola) {
                changeIcon(ChildState.BRINCANDO_1);
                System.out.println(this.getNome() + " está com a bola");
            } else {
                System.out.println(this.getNome() + " não está com a bola");
                try {
                    System.out.println(this.getNome() + " está tentando pegar a bola no cesto...");
                    changeIcon(ChildState.ESPERANDO);
                    JLabelWrapper.childTime.setText("Esperando aparecer bola no cesto");
                    cheio.acquire();
                    // Mostrar quantidade de bola nos cestos

                    JLabelWrapper.childTime.setText("");
                    System.out.println(this.getNome() + " pegou a bola no cesto");
                    vazio.release();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            long tempoInicialBrincando = System.currentTimeMillis();
            long tempo = 0;
            long tempo2 = tempoBrincando;

            changeIcon(ChildState.BRINCANDO_1);

            System.out.println(this.getNome() + " está brincando: " + tempo);


            boolean flag = false;

            long upTimer = 150;
            long upTimerReset = System.currentTimeMillis();
            int upTimerLimit = 150;
            while (tempo < tempoBrincando) {
                String brincadeiraTime = Integer.toString(Integer.parseInt(String.valueOf(tempo2 /1000)));
                JLabelWrapper.childTime.setText("Tempo da brincadeira: " + brincadeiraTime + "s");
                if (upTimer >= upTimerLimit) {
                    if (flag) {
                        changeIcon(ChildState.BRINCANDO_1);
                    } else {
                        changeIcon(ChildState.BRINCANDO_2);
                    }
                    upTimerReset = System.currentTimeMillis();
                    flag = !flag;
                }
                tempo2 = tempoBrincando - tempo;
                tempo = System.currentTimeMillis() - tempoInicialBrincando; // Tempo atual - tempo inicial
                upTimer = System.currentTimeMillis() - upTimerReset;
            }
            if (flag) {
                changeIcon(ChildState.BRINCANDO_1);

            }

            System.out.println(this.getNome() + " terminou de brincar: " + tempo/1000 + " se passaram");

            try {
                changeIcon(ChildState.CESTO_CHEIO);
                System.out.println(this.getNome() + " está tentando colocar a bola no cesto...");
                JLabelWrapper.childTime.setText("Está esperando por espaço no cesto");

                vazio.acquire();

                JLabelWrapper.childTime.setText("");
                System.out.println(this.getNome() + " conseguiu colocar a bola no cesto!");
                this.bola = false;
                cheio.release();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            changeIcon(ChildState.DESCANSANDO_1);

            long tempoInicialQuieta = System.currentTimeMillis();
            tempo = 0;
            tempo2 = tempoQuieta;

            System.out.println(this.getNome() + " está descansando " );


            boolean quietFlag = false;

            long quietTimer = 150;
            long quietTimerReset = System.currentTimeMillis();
            int quietTimerLimit = 150;
            while (tempo < tempoQuieta) {
                String quietaTempo = Integer.toString(Integer.parseInt(String.valueOf(tempo2 /1000)));
                JLabelWrapper.childTime.setText("Tempo quieta: " + quietaTempo + "s");

                if (quietTimer >= quietTimerLimit) {
                    if (quietFlag) {
                        changeIcon(ChildState.DESCANSANDO_1);
                    } else {
                        changeIcon(ChildState.DESCANSANDO_2);
                    }
                    quietTimerReset = System.currentTimeMillis();
                    quietFlag = !quietFlag;
                }
                tempo2 = tempoQuieta - tempo;
                tempo = System.currentTimeMillis() - tempoInicialQuieta;
                quietTimer = System.currentTimeMillis() - quietTimerReset;
            }
            if (quietFlag) {
                changeIcon(ChildState.DESCANSANDO_1);

            }

            System.out.println(this.getNome() + " terminou de descansar: " + (tempo/1000));
        }
    }

    private void changeIcon(ChildState state) {
        ImageIcon childIcon = getChildIcon(state);
        JLabelWrapper.childLabel.setIcon(childIcon);
        JLabelWrapper.childLabel.setText(getNome());
    }

    public static ImageIcon getChildIcon(ChildState state) {
        // Switch case que adiciona op valor da url para a imagem da criança
        URL imageChildURL;
        switch (state) {
            case ESPERANDO:
                imageChildURL = Main.class.getResource("/image/sem_Bola.png");
                break;
            case CESTO_CHEIO:
                imageChildURL = Main.class.getResource("/image/cesto_cheio.png");
                break;
            case DESCANSANDO_1:
                imageChildURL = Main.class.getResource("/image/acabouBrincar_1.png");
                break;
            case DESCANSANDO_2:
                imageChildURL = Main.class.getResource("/image/acabouBrincar_2.png");
                break;
            case BRINCANDO_1:
                imageChildURL = Main.class.getResource("/image/comBola_1.png");
                break;
            case BRINCANDO_2:
                imageChildURL = Main.class.getResource("/image/comBola_2.png");
                break;
            default:
                imageChildURL = null;
        }

        assert imageChildURL != null;

        return new ImageIcon(
                imageChildURL
        );
    }
}
