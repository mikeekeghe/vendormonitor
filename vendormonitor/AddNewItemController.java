/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vendormonitor;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author TN040764
 */
public class AddNewItemController implements Initializable {
    @FXML
    private Button btnClose;
    @FXML
    private TextField vendor_box;
    @FXML
    private TextField signed_box;
    @FXML
    private TextField description_box;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnClear;
    @FXML
    private TextField comments_box;
    @FXML
    private TextField tmp_box;
    @FXML
    private TextField expiry_box;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onClickClose(ActionEvent event) throws IOException {
        Parent clpage_parent = FXMLLoader.load(getClass().getResource("FXMLVendor.fxml"));
        Scene page_scene = new Scene(clpage_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(page_scene);
        app_stage.show();
    }

    @FXML
    private void onAddButtonClicked(ActionEvent event) {
            //insert into db if valid
        Date date = new Date();
        String theDate = date.toString();
        String vendor = vendor_box.getText();
        String signed = signed_box.getText();
        String description = description_box.getText();
        String comments = comments_box.getText();
        String expiry = expiry_box.getText();
        String tmp = tmp_box.getText();
        
        
        String query = "INSERT INTO vendorTbl (vendor,vendorTMP,DateOfExpiry,ITSRSignedDate,Comments,description,createdDate ) VALUES (" + 
                "'" + vendor +  "'," + "'" + tmp + "'," + "'" + expiry + "'," + "'" + signed + "'," + "'" +
                comments +  "','" + description +  "','" + theDate +  "');";
        
        System.out.println("Inserting\n" + query);
        insertStatement(query);
        System.out.println("Succesfully Inserted");
            vendor_box.clear();
         signed_box.clear();
         description_box.clear();
         comments_box.clear();
         expiry_box.clear();
         tmp_box.clear();
         

    }
    private void insertStatement(String insert_query){
        
    Connection c = null;
    Statement stmt = null;
    try {
      Class.forName("org.sqlite.JDBC");
      c = DriverManager.getConnection("jdbc:sqlite:dbVendors.db");
      c.setAutoCommit(false);
      System.out.println("Opened database successfully");
      stmt = c.createStatement(); 
      System.out.println("Our query was: " + insert_query);
      stmt.executeUpdate(insert_query);
      stmt.close();
      c.commit();
      c.close();
    }catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);  
        }
    }

    @FXML
    private void onClearButtonClicked(ActionEvent event) {
        vendor_box.clear();
         signed_box.clear();
         description_box.clear();
         comments_box.clear();
         expiry_box.clear();
          tmp_box.clear();
    }
    
}
