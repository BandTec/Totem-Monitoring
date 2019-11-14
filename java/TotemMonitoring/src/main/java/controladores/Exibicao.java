package controladores;

import conexao.banco.Conexao;
import conexao.slack.AlertaSlack;
import java.time.LocalDateTime;
import monitoramento.TelaTotem;
import monitoramento.Totem;

/**
 *
 * @author Massaru
 */
public class Exibicao {

    TelaTotem telaTotem;
    Totem totem = new Totem();
    Conexao conexao = new Conexao();
    AlertaSlack alerta;
    
    Double cpu,disco,memoria;
    LocalDateTime tempo;

    public Exibicao(TelaTotem telaTotem) {
        this.telaTotem = telaTotem;
        totem.capturarDados();
        cpu = totem.getCpu();
        disco = totem.getDisco();
        memoria = totem.getMemoria();
        tempo = totem.getTempo();
    }
    
    public void mostrarDados() {
        telaTotem.getLbCpu().setText(cpu.toString());
        telaTotem.getLbDisco().setText(disco.toString());
        telaTotem.getLbMemoria().setText(memoria.toString());
        telaTotem.getLbTempo().setText(tempo.toString());
        conexao.inserirDadosHW(cpu, memoria, disco);
        alerta = new AlertaSlack(cpu, memoria, disco);
    }
}
