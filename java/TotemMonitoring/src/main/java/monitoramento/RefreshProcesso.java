package monitoramento;

import com.mycompany.totemmonitoring.TelaProcessos;
import controladores.ExibicaoProcesso;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author victor.massao
 */
public class RefreshProcesso {

    ExibicaoProcesso exibicao;
    Totem totem;

    public RefreshProcesso(TelaProcessos tela) {
    exibicao = new ExibicaoProcesso(tela);
        totem = new Totem();
    }
    
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
