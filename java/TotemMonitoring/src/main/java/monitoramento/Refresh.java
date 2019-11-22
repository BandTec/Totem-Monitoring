package monitoramento;

import controladores.Exibicao;
import java.util.Timer;
import java.util.TimerTask;

public class Refresh {

    Exibicao exibicao;

    public Refresh(TelaTotem tela) {
        exibicao = new Exibicao(tela);
    }
    public void iniciar() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                exibicao.mostrarDados();
            }
        }, 0, 5000);
    }

}
