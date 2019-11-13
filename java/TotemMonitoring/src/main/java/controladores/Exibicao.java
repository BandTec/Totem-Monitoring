package controladores;

import com.mycompany.totemmonitoring.TelaProcessos;
import monitoramento.TelaTotem;
import monitoramento.Totem;

/**
 *
 * @author Massaru
 */
public class Exibicao {

    TelaTotem telaTotem;
//    TelaProcessos telaProcessos;
    Totem totem = new Totem();

    public Exibicao(TelaTotem telaTotem) {
        this.telaTotem = telaTotem;
    }
    
//    public Exibicao(TelaProcessos telaProcessos) {
//        this.telaProcessos = telaProcessos;
//    }
//    
    public void mostrarDados() {
        totem.capturarDados();
        telaTotem.getLbCpu().setText(totem.getCpu());
        telaTotem.getLbDisco().setText(totem.getDisco());
        telaTotem.getLbMemoria().setText(totem.getMemoria());
        telaTotem.getLbTempo().setText(totem.getTempo());
//        telaProcessos.getTextProcessos().setText(totem.getProcessos());
    }
}
