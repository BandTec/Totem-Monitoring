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

    Double cpu, disco, memoria;
    Integer qtdProcessos;
    LocalDateTime tempo;

    public Exibicao(TelaTotem telaTotem) {
        this.telaTotem = telaTotem;
    }

    public void captura() {
        totem.capturarDados();

        cpu = totem.getCpu();
        disco = totem.getDisco();
        memoria = totem.getMemoria();
        tempo = totem.getTempo();
        qtdProcessos = totem.getQtdProcessos();
    }

    public void mostrarDados() {
        captura();
        mandarAlerta();
        telaTotem.getLbCpu().setText(String.format("%.2f%%", cpu));
        telaTotem.getLbDisco().setText(String.format("%.2f", disco));
        telaTotem.getLbMemoria().setText(String.format("%.2f%%", memoria));
        telaTotem.getLbTempo().setText(tempo.toString());
        conexao.inserirDadosHW(cpu, memoria, disco, qtdProcessos);
        logg.info("CPU: {}; Disco: {}; Memoria: {}; Quantidade de processos: {}",
                String.format("%.2f", cpu), disco, String.format("%.2f", memoria), qtdProcessos);
    }

    private void mandarAlerta() {
        
        String message, color, statusAlerta;
        
        if (cpu > 1) {
            message = "Sua CPU esta ficando sobrecarregada";
            color = "#fe9918";
            statusAlerta = "Atencao";
            
            if (cpu > 90){
                message = "Sua CPU esta em risco";
                color = "#ec0505";
                statusAlerta = "Critico";
            }
            new AlertaSlack(message, color, statusAlerta);
        }
        if (memoria > 80) {
            message = "Voce esta ficando sem armazenamento na memoria";
            color = "#fe9918";
            statusAlerta = "Atencao";
            
            if (memoria < 90){
                message = "Sua memoria esta critica";
                color = "#ec0505";
                statusAlerta = "Critico";
            }
            new AlertaSlack(message, color, statusAlerta);
        }
        if (disco < 20) {
            message = "Voce esta ficando sem espaço no HD";
            color = "#fe9918";
            statusAlerta = "Atencao";
            
            if (disco < 10){
                message = "Seu HD esta cheio";
                color = "#ec0505";
                statusAlerta = "Critico";
            }
            new AlertaSlack(message, color, statusAlerta);
        }
        if (disco < 20 && memoria > 80){
            message = "Sua memoria e disco estao ficando sobrecarregados";
            color = "#ff0000";
            statusAlerta = "Atencao";
            
            if (disco < 10 && memoria > 90){
                message = "Disco e memoria em risco";
                color = "#ec0505";
                statusAlerta = "Critico";
            }
            new AlertaSlack(message, color, statusAlerta);
        }
    }
}
