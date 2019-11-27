package conexao.slack;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author victor.massao
 */
public class AlertaSlack {

    public void enviarAlerta(String message, String color, String statusAlerta) {
        try {
            String json = String.format("{ 'attachments': [{'text':'%s',"
                    + " 'color':'%s',"
                    + "'pretext':'%s',"
                    + " 'footer':'Cardoso',"
                    + " 'ts':''}] }", message, color, statusAlerta);

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
