package controladores;

import monitoramento.TelaTotem;
import monitoramento.Totem;

/**
 *
 * @author Massaru
 */
public class Exibicao {

    TelaTotem telaTotem;
    Totem totem = new Totem();

    public Exibicao(TelaTotem telaTotem) {
        this.telaTotem = telaTotem;
    }
    
    public void mostrarDados() {
        totem.capturarDados();
        telaTotem.getLbCpu().setText(totem.getCpu().toString());
        telaTotem.getLbDisco().setText(totem.getDisco().toString());
        telaTotem.getLbMemoria().setText(totem.getMemoria().toString());
        telaTotem.getLbTempo().setText(totem.getTempo().toString());
    }
}
