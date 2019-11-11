/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    public static void main(String[] args) {
        
        try {
            URL url = new URL("https://hooks.slack.com/services/TPX0T3M7V/"
                    + "BPYUG1SD8/nByEfYcywKkEDLQGMuqjnUPL");
            HttpURLConnection connection = 
                    (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type","application/json");
            
            connection.setDoOutput(true);
            DataOutputStream post = new DataOutputStream(connection.getOutputStream());
            post.writeBytes(String.format("{ 'attachments': [{'text':'oioioi', 'color':'#000000',"
                    + "'pretext':'Erro!', 'footer':'Massao Gayzasso', 'ts':''}] }"));
            post.flush();
            post.close();
            
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code : " + responseCode);
 
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
	}
    }
}
