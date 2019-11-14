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
        new AlertaSlack(totem.getCpu(), totem.getMemoria(), totem.getDisco());
    }
}
