package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
	
	@FXML
	private TextField usuario;
	
	@FXML
	private PasswordField contraseña;
	
	@FXML
	private Button btnIN;

	@FXML 
	private Label verificar;
	
	Connection con = null;
	PreparedStatement reparedStatement = null;
	ResultSet resultset = null;
	
	
	@FXML
	void ingresar(ActionEvent event) throws SQLException{
		String login = usuario.getText();
		String clave = contraseña.getText();
		Conexion conect = new Conexion();
		conect.conectar();
        if(conect.isConectado()){
            String query = "SELECT codigo from usuarios where nombre = '"+login+"' AND contraseña = '"+clave+"'";
            try (Statement stm = conect.getCon().createStatement()){
            	System.out.println("entree al try de logincontroller database");
                ResultSet rst = stm.executeQuery(query);
                if(rst.next()){ 
                	System.out.println("entree al if dentro del try de logincontroller database");
                    Stage stage = (Stage) btnIN.getScene().getWindow();
                    stage.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("home2.fxml"));
                    Parent root = loader.load();
                    //Parent root = (new FXMLLoader(getClass().getResource("home2.fxml"))).load();
                    Scene scene = new Scene(root);
                    stage = new Stage();
             
                    stage.setTitle("Home");
                    stage.setScene(scene);
               
                    stage.show();
                    conect.desconectar();
                       
                    
                }
                else
                	verificar.setText("Usuario o Clave no validos");
                
            } catch (Exception e) {
                System.out.println("ERROR: Aborting...");
                e.printStackTrace();
               
        }
        }
	}

}
