/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendormonitor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author TN040764
 */
public class VendorMonitor extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        checkVendorTbl();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLVendor.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void checkVendorTbl() {
        System.out.println("creating db");
        String sqlCreateIncomeTxn = "CREATE TABLE IF NOT EXISTS vendorTbl ("
                + "id	INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,"
                + "vendor	TEXT,"
                + "vendorTMP	TEXT,"
                + "DateOfExpiry	TEXT,"
                + "ITSRSignedDate	TEXT,"
                + "Comments	TEXT,"
                + "description	TEXT,"
                 + "createdDate	TEXT"
                + ")";
        System.out.println("creating db script is "+sqlCreateIncomeTxn);
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:dbVendors.db");
            stmt = conn.createStatement();
            stmt.execute(sqlCreateIncomeTxn);
            System.out.println("After creating db");

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

}
