package controladores;

import monitoramento.TelaTotem;
import monitoramento.Totem;

/**
 *
 * @author Massaru
 */
public class Exibicao {

    TelaTotem tela;
    Totem totem = new Totem();

    public Exibicao(TelaTotem tela) {
        this.tela = tela;
    }
    
    public void mostrarDados() {
        totem.capturarDados();
        tela.getLbCpu().setText(totem.getCpu());
        tela.getLbDisco().setText(totem.getDisco());
        tela.getLbMemoria().setText(totem.getMemoria());
        tela.getLbTempo().setText(totem.getTempo());
    }
}
