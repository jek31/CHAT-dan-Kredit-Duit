/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication4;

import java.awt.Frame;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javax.swing.JOptionPane;

/**
 * 2017
 * @author JACK
 */
public class FXMLDocumentController implements Initializable {
    @FXML
    private TextField field1;
    @FXML
    private TextField field2;
    @FXML
    private TextField field3;
    @FXML
    private TextField field4;
    @FXML
    private TextField field5;
    @FXML
    private Button button;
    @FXML
    private Button button1;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        //Pemrosesan Hasil Input NIlai Pinjaman / Tenor
        Double a = Double.parseDouble(field1.getText()) / Double.parseDouble(field2.getText());
        //Suku Bunga
        Double b = Double.parseDouble(field3.getText()) / 12;
        field4.setText(b.toString().substring(0,3));
        //Pemrosesan nilai Pinjaman
        Double c = a + (Double.parseDouble(field1.getText())*(Double.parseDouble(field4.getText())/100));
        field5.setText(c.toString().substring(0));
        
    }
    //Menghapus semua isi inputan
    @FXML
    private void handleButton1Action(ActionEvent event) {
    field1.clear();
    field2.clear();
    field3.clear();
    field4.clear();
    field5.clear();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
