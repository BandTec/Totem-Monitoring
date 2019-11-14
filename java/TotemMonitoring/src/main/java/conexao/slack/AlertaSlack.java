package conexao.slack;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author Aluno
 */
public class AlertaSlack {

    Double cpu, memoria, disco;

    public AlertaSlack(Double cpu, Double memoria, Double disco) {
        this.cpu = cpu;
        this.memoria = memoria;
        this.disco = disco;
        verificarParams();
    }
    
    private void verificarParams() {
        if (memoria > 1) {
            System.out.println("ENTROU NO VERIFICARPARAMNS");
            enviarAlerta("Sua memoria esta cagada");
        }
    }

    private void enviarAlerta(String message) {
        try {
            String json = String.format("{ 'attachments': [{'text':'%s',"
                    + " 'color':'#ff9104',"
                    + "'pretext':'Cuidado!',"
                    + " 'footer':'Massao',"
                    + " 'ts':''}] }", message);

            URL url = new URL("https://hooks.slack.com/services/TPX0T3M7V/"
                    + "BPYUG1SD8/nByEfYcywKkEDLQGMuqjnUPL");
            HttpURLConnection connection
                    = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            connection.setDoOutput(true);
            try (DataOutputStream post = new DataOutputStream(connection.getOutputStream())) {
                System.out.println(json);
                System.out.println(json);
                post.writeBytes(json);
                post.flush();
            }

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code : " + responseCode);

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
