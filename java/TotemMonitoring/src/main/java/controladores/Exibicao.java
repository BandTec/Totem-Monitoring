package controladores;

import conexao.banco.Conexao;
import conexao.slack.AlertaSlack;
import java.awt.Color;
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

    AlertaSlack alerta = new AlertaSlack();

    Double cpu, disco, memoria;
    Integer qtdProcessos;
    LocalDateTime tempo;
    Integer id_totem = 4;

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
        telaTotem.getLbDisco().setText(String.format("%.2f%%", disco));
        telaTotem.getLbMemoria().setText(String.format("%.2f%%", memoria));
        telaTotem.getLbTempo().setText(tempo.toString());
        conexao.inserirDadosHW(cpu, memoria, disco, qtdProcessos, id_totem);

    }

    private void mandarAlerta() {

        String message, color, statusAlerta;

        if (cpu > 90) {
            message = String.format("Sua CPU esta em risco, no totem %s", id_totem);
            color = "#ec0505";
            statusAlerta = "Critico";
            telaTotem.getLbCpu().setForeground(new Color(255, 0, 0));

            logg.warn("Nivel de CPU anormal: {}", String.format("%.2f", cpu));

            alerta.enviarAlerta(message, color, statusAlerta);
        } else if (cpu > 80) {
            message = String.format("Sua CPU esta ficando sobrecarregada, no totem %s", id_totem);
            color = "#fe9918";
            statusAlerta = "Atencao";
            telaTotem.getLbCpu().setForeground(new Color(255, 225, 4));

            logg.info("Nivel de CPU anormal: {}", String.format("%.2f", cpu));

            alerta.enviarAlerta(message, color, statusAlerta);
        } else {
            telaTotem.getLbCpu().setForeground(new Color(0, 255, 0));
        }

        if (memoria > 90) {
            message = String.format("Sua memoria esta critica, no totem %s", id_totem);
            color = "#ec0505";
            statusAlerta = "Critico";
            telaTotem.getLbMemoria().setForeground(new Color(255, 0, 0));

            logg.warn("Nivel de memoria anormal: {}", String.format("%.2f", memoria));

            alerta.enviarAlerta(message, color, statusAlerta);
        } else if (memoria > 80) {
            message = String.format("Voce esta ficando sem armazenamento na memoria, no totem %s", id_totem);
            color = "#fe9918";
            statusAlerta = "Atencao";
            telaTotem.getLbMemoria().setForeground(new Color(255, 225, 4));

            logg.info("Nivel de memoria anormal: {}", String.format("%.2f", memoria));

            alerta.enviarAlerta(message, color, statusAlerta);
        } else {
            telaTotem.getLbMemoria().setForeground(new Color(0, 255, 0));
        }

        if (disco > 97) {
            message = String.format("Seu HD esta cheio, no totem %s", id_totem);
            color = "#ec0505";
            statusAlerta = "Critico";
            telaTotem.getLbDisco().setForeground(new Color(255, 0, 0));

            logg.warn("Nivel de disco anormal: {}", String.format("%.2f", disco));

            alerta.enviarAlerta(message, color, statusAlerta);
        } else if (disco > 80) {
            message = String.format("Voce esta ficando sem espaço no HD, no totem %s", id_totem);
            color = "#fe9918";
            statusAlerta = "Atencao";
            telaTotem.getLbDisco().setForeground(new Color(255, 225, 4));

            logg.info("Nivel de disco anormal: {}", String.format("%.2f", disco));

            alerta.enviarAlerta(message, color, statusAlerta);
        } else {
            telaTotem.getLbDisco().setForeground(new Color(0, 255, 0));
        }
    }
}
