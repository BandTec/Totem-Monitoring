package com.mycompany.totemmonitoring;

import monitoramento.TelaTotem;

public class AlteradorSingleton {

    private static AlteradorSingleton instance;
    
    private TelaComecar telaComecar;
    private TelaTotem telaTotem;
    private TelaProcessos telaProcessos;

    private AlteradorSingleton() {
        telaComecar = new TelaComecar();
        telaTotem = new TelaTotem();
        telaProcessos = new TelaProcessos();
        
        telaComecar.setVisible(true);
    }
    
    public static AlteradorSingleton getInstance() {
        if (instance == null) {
            instance =  new AlteradorSingleton();
        }
        return instance;
    }
    
    public void checkIn() {
        telaComecar.setVisible(false);
        telaProcessos.setVisible(true);
        telaTotem.setVisible(true);
    }
    
    public void checkOff() {
        telaComecar.setVisible(true);
        telaProcessos.setVisible(false);
        telaTotem.setVisible(false);  
    }
}
