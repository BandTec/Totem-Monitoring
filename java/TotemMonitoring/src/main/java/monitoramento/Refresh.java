package monitoramento;

import com.mycompany.totemmonitoring.TelaProcessos;
import controladores.Exibicao;
import java.util.Timer;
import java.util.TimerTask;

public class Refresh {

    Exibicao exibicao;
    Totem totem;

    public Refresh(TelaTotem tela) {
        exibicao = new Exibicao(tela);
        totem = new Totem();
    }
    
//    public Refresh(TelaProcessos tela) {
//        exibicao = new Exibicao(tela);
//        totem = new Totem();
//    }

    public void iniciar() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                exibicao.mostrarDados();
            }
        },0,5000);
    }

}
