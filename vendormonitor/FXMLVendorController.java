/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendormonitor;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import vendormonitor.entities.VendorRecord;

/**
 *
 * @author TN040764
 */
public class FXMLVendorController implements Initializable {

    String orderby;

    String ascdesc;

    private ObservableList VendorRecordData;
    String myTatal;
    @FXML
        private TableView tableVendor;
    @FXML
    TableColumn idCol;
    @FXML
    TableColumn vendornameCol;
    @FXML
    TableColumn vendorTMPCol;
    @FXML
    TableColumn DateOfExpiryCol;
    @FXML
    TableColumn ITSRSignedDateCol;
    @FXML
    TableColumn commentsCol;
    @FXML
    TableColumn descriptionCol;
    //START | SQLITE
    private static Connection con;
    private static Statement stat;
    private PreparedStatement prep;
    //END | SQLITE
    private String id;
    private String vendorName;
    private String vendorTMP;
    private String DateOfExpiry;
    private String ITSRSignedDate;
    private String Comments;
    private String Description;

    @FXML
            private TextField vendor_box;
    @FXML
        private TextField tmp_box;
    @FXML
        private TextField expiry_box;
    @FXML
        private TextField itsr_signed_box;
    @FXML
        private TextField comments_box;
    @FXML
        private TextField description_box;
        
            
        @FXML
        private Button addButton;
        
        @FXML
        private Button closeButton;
        
        @FXML
        private Button updateButton;
        
        @FXML
        private Button deleteButton;

    private void onClickClose(ActionEvent event) throws IOException {

        Parent clpage_parent = FXMLLoader.load(getClass().getResource("homePage.fxml"));
        Scene page_scene = new Scene(clpage_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(page_scene);
        app_stage.show();
    }

    private void refreshList() {
        VendorRecordData = FXCollections.observableArrayList();

        idCol.setCellValueFactory(
                new PropertyValueFactory<VendorRecord, String>("id")
        );
        vendornameCol.setCellValueFactory(
                new PropertyValueFactory<VendorRecord, String>("vendor")
        );
        vendorTMPCol.setCellValueFactory(
                new PropertyValueFactory<VendorRecord, String>("vendorTMP")
        );
        DateOfExpiryCol.setCellValueFactory(
                new PropertyValueFactory<VendorRecord, String>("DateOfExpiry")
        );
        ITSRSignedDateCol.setCellValueFactory(
                new PropertyValueFactory<VendorRecord, String>("ITSRSignedDate")
        );
        commentsCol.setCellValueFactory(
                new PropertyValueFactory<VendorRecord, String>("Comments")
        );
        descriptionCol.setCellValueFactory(
                new PropertyValueFactory<VendorRecord, String>("description")
        );
        try {

            // SQLiteConfig config = new SQLiteConfig();
            con = DriverManager.getConnection("jdbc:sqlite:dbVendors.db");
            stat = con.createStatement();
            System.out.println("Opened database successfully");
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM vendorTbl");
            while (rs.next()) {
                VendorRecord myVR = new VendorRecord();

                vendorName = rs.getString("vendor");
                vendorTMP = rs.getString("vendorTMP");
                DateOfExpiry = rs.getString("DateOfExpiry");
                ITSRSignedDate = rs.getString("ITSRSignedDate");
                Comments = rs.getString("Comments");
                Description = rs.getString("description");

                System.out.println(" New vendorName = " + vendorName);
                System.out.println("vendorTMP = " + vendorTMP);
                System.out.println("DateOfExpiry = " + DateOfExpiry);
                System.out.println("ITSRSignedDate = " + ITSRSignedDate);
                System.out.println("Comments = " + Comments);
                System.out.println("Description = " + Description);

                myVR.setId(rs.getString("id"));
                myVR.setVendor(rs.getString("vendor"));
                myVR.setVendorTMP(rs.getString("vendorTMP"));
                myVR.setDateOfExpiry(rs.getString("DateOfExpiry"));
                myVR.setITSRSignedDate(rs.getString("ITSRSignedDate"));
                myVR.setComments(rs.getString("Comments"));
                myVR.setDescription(rs.getString("description"));
                VendorRecordData.add(myVR);
            }
            tableVendor.setItems(VendorRecordData);

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("before refresh");
        refreshList();
        System.out.println("after refresh");
    }

    @FXML
    private void onAddButtonClicked(ActionEvent event) throws IOException {
        System.out.println("You clicked Add Item!");
        Parent addnew_page_parent = FXMLLoader.load(getClass().getResource("AddNewItem.fxml"));
        Scene addnew_page_scene = new Scene(addnew_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(addnew_page_scene);
        app_stage.show();
    }

    @FXML
    private void onCloseButtonClicked(ActionEvent event) throws IOException {
        Parent clpage_parent = FXMLLoader.load(getClass().getResource("FXMLVendor.fxml"));
        Scene page_scene = new Scene(clpage_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(page_scene);
        app_stage.show();
    }
    
    @FXML
        private void onClickTable(javafx.scene.input.MouseEvent event) {
        vendor_box.clear();
        tmp_box.clear();
        expiry_box.clear();
        itsr_signed_box.clear();
        comments_box.clear();
        description_box.clear();

        VendorRecord myVendorRecord = new VendorRecord();
        Date date = new Date();
        String theDate = date.toString();
        String VendorRecord_name;
        String VendorRecord_vendorTMP;
        String VendorRecord_DateOfExpiry;
        String VendorRecord_ITSRSignedDate;
        String VendorRecord_comments;
        String VendorRecord_description;

        myVendorRecord = (VendorRecord) tableVendor.getSelectionModel().getSelectedItem();
        id = myVendorRecord.getId();
        System.out.println("id is: "+id);
        VendorRecord_name = myVendorRecord.getVendor();
        VendorRecord_vendorTMP = myVendorRecord.getVendorTMP();
        VendorRecord_DateOfExpiry = myVendorRecord.getDateOfExpiry();
        VendorRecord_ITSRSignedDate = myVendorRecord.getITSRSignedDate();
        VendorRecord_comments = myVendorRecord.getComments();
        VendorRecord_description = myVendorRecord.getDescription();

        vendor_box.setText(VendorRecord_name);
        tmp_box.setText(VendorRecord_DateOfExpiry);
        expiry_box.setText(VendorRecord_vendorTMP);
        itsr_signed_box.setText(VendorRecord_ITSRSignedDate);
        comments_box.setText(VendorRecord_comments);
        description_box.setText(VendorRecord_description);

    }
    
       @FXML
    private boolean onClickUpdate(ActionEvent event) throws IOException {
        VendorRecord myVR = new VendorRecord();
        Date date = new Date();
        int theId = Integer.parseInt(id);
        String theDate = date.toString();
        //format for report queries
        Calendar cal = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cal.add(Calendar.DATE, 0);
        String VendorRecord_name = vendor_box.getText();
        String VendorRecord_DateOfExpiry = tmp_box.getText().toUpperCase();
        String VendorRecord_vendorTMP = expiry_box.getText().toUpperCase();
        String VendorRecord_ITSRSignedDate = itsr_signed_box.getText().toUpperCase();
        //String VendorRecord_comments = comments_box.getText().toUpperCase();
        String VendorRecord_comments ;
        VendorRecord_comments = (comments_box.getText()== null) ? "":comments_box.getText();
         VendorRecord_comments =  VendorRecord_comments.toUpperCase();
        String VendorRecord_description ;
        VendorRecord_description = (description_box.getText()== null) ? "":description_box.getText();
          VendorRecord_description =  VendorRecord_description.toUpperCase();
//        VendorRecord_description = (description_box.getText().toUpperCase()== null) ? "":description_box.getText().toUpperCase();

        System.out.println("VendorRecord_name is: " + VendorRecord_name);

        if ((VendorRecord_name.trim().length() == 0) || (VendorRecord_name == "") || (VendorRecord_name.trim().isEmpty())) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Information Dialog");
//            alert.setHeaderText(null);
//            alert.setContentText("Please Highlight A VendorRecord Record");
//
//            alert.showAndWait();
            //System.clearProperty(VendorRecord_name);
            return false;
        }
        //myVR.setId(rs.getString("id"));

        myVR.setVendor(VendorRecord_name);
        myVR.setDescription(VendorRecord_description);
        myVR.setDateOfExpiry(VendorRecord_DateOfExpiry);
        myVR.setVendorTMP(VendorRecord_vendorTMP);
        myVR.setITSRSignedDate(VendorRecord_ITSRSignedDate);
        myVR.setComments(VendorRecord_comments);

        ObservableList highlightedVendorRecordRecord, allVendorRecordRecords;

        highlightedVendorRecordRecord = tableVendor.getSelectionModel().getSelectedItems();

        System.out.println("VendorRecord_name is : " + VendorRecord_name);
        System.out.println("the ID IS: " + theId);
        String query = "UPDATE vendorTbl set "
                + "DateOfExpiry ='"
                + VendorRecord_DateOfExpiry + "',"
                + "vendorTMP ='"
                + VendorRecord_vendorTMP + "',"
                + "ITSRSignedDate ='"
                + VendorRecord_ITSRSignedDate + "',"
                + "Comments ='"
                + VendorRecord_comments + "',"
                + "description ='"
                + VendorRecord_description + "'"
                + " WHERE id = "
                + theId;

        System.out.println("updating\n" + query);
        updateStatement(query);

//        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
//        alert3.setTitle("Information Dialog");
//        alert3.setHeaderText(null);
//        alert3.setContentText("Record updated Succesfully.");
//
//        alert3.showAndWait();

        System.out.println("Succesfully Updated");

        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("FXMLVendor.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();

        return true;

    }

    @FXML
        private void onClickExit(ActionEvent event) {
        Platform.exit();
    }
    
    @FXML
    private void onClickDelete(ActionEvent event) throws IOException {
        int theId = Integer.parseInt(id);
        String VendorRecord_name = vendor_box.getText();

        if ((VendorRecord_name.trim().length() == 0) || (VendorRecord_name == "") || (VendorRecord_name.trim().isEmpty())) {
//            Alert alert = new Alert(Alert.AlertType.INFORMATION);
//            alert.setTitle("Information Dialog");
//            alert.setHeaderText(null);
//            alert.setContentText("Please Highlight A Class Room Record");
//
//            alert.showAndWait();
            System.clearProperty(VendorRecord_name);
        }
        //myVR.setId(rs.getString("id"));

        ObservableList highlightedVendorRecordRecord, allVendorRecordRecords;
        allVendorRecordRecords = tableVendor.getItems();
        highlightedVendorRecordRecord = tableVendor.getSelectionModel().getSelectedItems();
        System.out.println("VendorRecord_name is : " + VendorRecord_name);

        String query = "DELETE FROM vendorTbl "
                + " WHERE id = "
                + theId;

        System.out.println("updating\n" + query);
        updateStatement(query);

//        Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
//        alert3.setTitle("Information Dialog");
//        alert3.setHeaderText(null);
//        alert3.setContentText("Record Deleted Succesfully.");
//
//        alert3.showAndWait();

        System.out.println("Succesfully Updated");

        Parent TodayReport_page_parent = FXMLLoader.load(getClass().getResource("FXMLVendor.fxml"));
        Scene TodayReport_page_scene = new Scene(TodayReport_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.hide(); //optional
        app_stage.setScene(TodayReport_page_scene);
        app_stage.show();

    }

    private void updateStatement(String update_query) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:dbVendors.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");
            stmt = c.createStatement();
            System.out.println("Our query was: " + update_query);
            stmt.executeUpdate(update_query);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    



}



