package monitoramento;

import java.util.Timer;
import java.util.TimerTask;

public class Refresh {

    Timer timer;

    public Refresh(int tempo) {
        timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 5000);

    }

    TimerTask task = new TimerTask() {
        Totem totem = new Totem();

        @Override
        public void run() {
            System.out.println("Esta merda");
            totem.capturarDados();
            System.out.println(totem.getProcessos());
        }
    };
//    class RemindTask extends TimerTask {
//        public void run() {
//            System.out.println("Time's up!");
//            timer.cancel(); //Terminate the timer thread
//        }
//    }

    public static void main(String args[]) {
        new Refresh(5);
        System.out.println("Task scheduled.");
    }
}
