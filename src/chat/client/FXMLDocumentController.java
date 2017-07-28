package chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLDocumentController implements Initializable {
    @FXML
    private TextArea txtMessages;
    
    @FXML
    private TextField txtSend;
    
    private BufferedReader in;
    private PrintWriter out;
    
    @FXML
    private void sendMessage(){
        String message = txtSend.getText().trim();
        
        txtMessages.appendText("Me: "+message+ "\n");
        out.println(message);
        out.flush();
        txtSend.setText(null);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Thread (() -> {
            try {
               
               Socket socket = new Socket ("localhost", 1357);
                System.out.println("Connected to server!");
                
                in = new BufferedReader (new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream());
                
                while (true){
                    String input = in.readLine();
                    
                    Platform.runLater(() -> {
                        txtMessages.appendText("Server: "+input+ "\n");
                    });
                }
                
            } catch (IOException ex) {
                System.err.println("Socket Error");
            }
        }).start();  
    }
}
