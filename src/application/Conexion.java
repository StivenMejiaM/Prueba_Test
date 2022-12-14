package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion {
	static final String UNUSED = "unused";
	private Connection con;
	private String url="jdbc:mysql://localhost:3306/almacen_velez?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String nombre="root";
    private String contraseņa="";
    private boolean conectado;

    public Connection getCon() {
        return con;
    }

    public boolean isConectado() {
        return conectado;
    }

    public Conexion() {
        
        this.con = null;
        this.conectado = false;
        
    }
    
    public void conectar() throws SQLException{
       try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection(url,nombre,contraseņa);
            this.conectado=true;
            System.out.println("conectado");
        } catch (ClassNotFoundException e) {
            this.conectado=false;
            e.printStackTrace();
        }
    }
    
    public void desconectar(){
        if(this.conectado){
            this.conectado=false;
            try{
                this.con.close();
            }
            catch(SQLException ex){
                this.con=null;
            }
        }
    }

}
