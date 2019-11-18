package controladores;

import conexao.Conexao;
import monitoramento.TelaTotem;
import monitoramento.Totem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Massaru
 */
public class Exibicao {

    TelaTotem telaTotem;
    Totem totem = new Totem();
    Conexao conexao = new Conexao();
    private final Logger logg = LoggerFactory.getLogger(Totem.class);

    public Exibicao(TelaTotem telaTotem) {
        this.telaTotem = telaTotem;
    }

    public void mostrarDados() {
        totem.capturarDados();
        telaTotem.getLbCpu().setText(totem.getCpu().toString());
        telaTotem.getLbDisco().setText(totem.getDisco().toString());
        telaTotem.getLbMemoria().setText(totem.getMemoria().toString());
        telaTotem.getLbTempo().setText(totem.getTempo().toString());
        conexao.inserirDadosHW(totem.getCpu(), totem.getMemoria(), totem.getDisco());
        logg.info("CPU: {}; Disco: {}; Memoria: {}", totem.getCpu(), totem.getDisco(), totem.getMemoria());
    }
}
