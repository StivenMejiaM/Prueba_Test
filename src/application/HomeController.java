package application;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

	public class HomeController {

	    @FXML
	    private MenuItem Ventas;

	    @FXML
	    private Menu acciones;

	    @FXML
	    private ImageView anuncio;

	    @FXML
	    private MenuBar nav;

	    @FXML
	    private TextField portada;

	    @FXML
	    void ventasMenuClick(ActionEvent event) {
	    	try {
	    		Parent root =(new FXMLLoader(getClass().getResource("ventana.fxml"))).load();
	    		System.out.println("entra2");
	    		Scene scene = new Scene(root);
	    		Stage teatro = new Stage();
	    		teatro.setTitle("clientes");
	    		teatro.setScene(scene);
	    		teatro.show();
	    	}catch(IOException e) {
	    		e.printStackTrace();
	    	}

	    }

	    
	    
}
