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
        telaTotem.getLbCpu().setText(String.format("%.2f", totem.getCpu()));
        telaTotem.getLbDisco().setText(totem.getDisco().toString());
        telaTotem.getLbMemoria().setText(totem.getMemoria().toString());
        telaTotem.getLbTempo().setText(totem.getTempo().toString());
        conexao.inserirDadosHW(totem.getCpu(), totem.getMemoria(), totem.getDisco(), totem.getQtdProcessos());
        logg.info("CPU: {}; Disco: {}; Memoria: {}; Quantidade de processos: {}", String.format("%.2f", totem.getCpu()), totem.getDisco(), totem.getMemoria(), totem.getQtdProcessos());
    }
}
