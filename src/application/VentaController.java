package application;

import java.awt.Button;
import java.awt.TextField;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;


public class VentaController {
	Connection con = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    String dato, query;
    Conexion conect = new Conexion();

    @FXML
    private Button btnGuardar;

    @FXML
    private ComboBox<String> cmbClient;

    @FXML
    private ComboBox<String> cmbProduct;

    @FXML
    private TextField txtCant;

    @FXML
    void clickGuardar(MouseEvent event) throws SQLException {
        String cliente = cmbClient.getValue();
        String producto = cmbProduct.getValue();
        String cantidad = txtCant.getText();
        if (cliente==null || cliente.isEmpty()) {
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Validacion de datos");
            alerta.setContentText("Por favor seleccione un cliente");
            alerta.showAndWait();
        }
        else if(producto==null || producto.isEmpty()){
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Validacion de datos");
            alerta.setContentText("Por favor seleccione un producto");
            alerta.showAndWait();
        }
        else if(cantidad==null || cantidad.isEmpty() || !esValido(cantidad)){
            Alert alerta = new Alert(AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setTitle("Validacion de datos");
            alerta.setContentText("Por favor seleccione un producto");
            alerta.showAndWait();
        }
        else{
            
            conect.conectar();
            try (Statement stm = conect.getCon().createStatement()){
                String[] tmp = cliente.split(" ");
                int clie = Integer.parseInt(tmp[0]);
                tmp = producto.split(" ");
                int prod = Integer.parseInt(tmp[0]);
                int cant = Integer.parseInt(cantidad);
                query = "INSERT INTO ventas(cliente,producto,cantidad) VALUES ("+clie+","+prod+","+cant+")";
                int resu = stm.executeUpdate(query);
                if(resu!=0){
                    System.out.println("Datos Insertados con exito");
                    ((List<String>) txtCant).clear();
                }
                else{
                    System.out.println("Error al registrar la venta");
                }
                
            } catch (Exception e) {
            }
            
        }
        
        
    }
    
    private boolean esValido(String valor){
            boolean sw = false;
            try{
                int dato = Integer.parseInt(valor);
                sw= dato>0;
            }
            catch (NumberFormatException e){
                sw = false;
            }
            return sw;
    }
    
    @FXML
    void initialize() throws IOException, SQLException{
        
        
        ResultSet rst;
               
        conect.conectar();
        System.out.println("Voy bien antes del combo");
        
        query = "SELECT id,nombre,apellido from clientes order by apellido,nombre";
        try (Statement stm = conect.getCon().createStatement()){ 
            System.out.println("Voy bien de la consulta combo");
            rst = stm.executeQuery(query);
            System.out.println("Voy bien dentro combo");
            while (rst.next()) {
                dato = String.format("%d %s %s", rst.getInt("id"), rst.getString("nombre"), rst.getString("apellido"));
                cmbClient.getItems().add(dato);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
       
        query = "SELECT codigo,nombre from productos order by nombre";
        try (Statement stm = conect.getCon().createStatement()){ 
            rst = stm.executeQuery(query);
            while (rst.next()) {
                dato = String.format("%d %s", rst.getInt("codigo"), rst.getString("nombre"));
                cmbProduct.getItems().add(dato);
            }
        } catch (Exception e) {
        }
        
    }

}

