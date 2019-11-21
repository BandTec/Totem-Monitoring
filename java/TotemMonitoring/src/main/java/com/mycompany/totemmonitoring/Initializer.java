package com.mycompany.totemmonitoring;

import monitoramento.TelaTotem;

/**
 *
 * @author victor.massao
 */
public class Initializer {

    public static void start() {
        TelaProcessos telaprocessos = new TelaProcessos();
        TelaTotem telaTotem = new TelaTotem();

        telaprocessos.setVisible(true);
        telaTotem.setVisible(true);
    }
}
