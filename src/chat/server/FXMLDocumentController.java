package chat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
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
                System.out.println("waiting for client...");
                ServerSocket serverSocket = new ServerSocket(1357);
                Socket socket = serverSocket.accept();
                System.out.println("Client Connected");
                
                in = new BufferedReader (new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream());
                
                while (true){
                    String input = in.readLine();
                    
                    Platform.runLater(() -> {
                        txtMessages.appendText("Client: "+input+ "\n");
                    });
                }
                
            } catch (IOException ex) {
                System.err.println("ServerSocket Error");
                ex.printStackTrace();
            }
        }).start();  
    }
}
