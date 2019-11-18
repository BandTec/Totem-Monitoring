package controladores;

import com.mycompany.totemmonitoring.TelaProcessos;
import monitoramento.Totem;

/**
 *
 * @author victor.massao
 */
public class ExibicaoProcesso {

    TelaProcessos telaProcessos;
    Totem totem = new Totem();

    public ExibicaoProcesso(TelaProcessos tela) {
        this.telaProcessos = tela;
    }

    public void mostrarDados() {
        totem.capturarDados();
//        System.out.println("QUANTIDADE DE PROCESSOS: " + totem.getProcessos());
        telaProcessos.getTextProcessos().setText(totem.getProcessos());
    }

}
