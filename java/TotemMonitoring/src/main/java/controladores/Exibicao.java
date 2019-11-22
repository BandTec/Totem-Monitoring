package controladores;

import conexao.banco.Conexao;
import conexao.slack.AlertaSlack;
import java.time.LocalDateTime;
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

    AlertaSlack alerta;
    
    Double cpu,disco,memoria;
    Integer qtdProcessos;
    LocalDateTime tempo;

    public Exibicao(TelaTotem telaTotem) {
        this.telaTotem = telaTotem;
        totem.capturarDados();
        cpu = totem.getCpu();
        disco = totem.getDisco();
        memoria = totem.getMemoria();
        tempo = totem.getTempo();
        qtdProcessos = totem.getQtdProcessos();
    }

    public void mostrarDados() {
        telaTotem.getLbCpu().setText(String.format("%.2f", cpu);
        telaTotem.getLbDisco().setText(disco.toString());
        telaTotem.getLbMemoria().setText(memoria.toString());
        telaTotem.getLbTempo().setText(tempo.toString());
        conexao.inserirDadosHW(cpu, memoria, disco, qtdProcessos);
        alerta = new AlertaSlack(cpu, memoria, disco);
        logg.info("CPU: {}; Disco: {}; Memoria: {}; Quantidade de processos: {}",
            String.format("%.2f", totem.getCpu()), totem.getDisco(), totem.getMemoria(), totem.getQtdProcessos());
    }
}
