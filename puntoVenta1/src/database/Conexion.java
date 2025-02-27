package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";  // Actualizado
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";  // URL sigue igual
    
    private final static String DB = "puntoventa";  
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "4321";
    
    public Connection conectar;
    public static Conexion singleConnection;
    
    private Conexion() {
        this.conectar = null;
    }
    
    public Connection conectar() {
        try{
            Class.forName(DB_DRIVER);  // Actualizado
            this.conectar = DriverManager.getConnection(
            DB_URL + DB + "?serverTimezone=UTC",  DB_USERNAME, DB_PASSWORD  // Añadido serverTimezone
            );
        }catch(ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
            System.exit(0);   
        }
        return this.conectar;
    }    
    
    public static Conexion getInstance() {
        if(singleConnection == null){
            singleConnection = new Conexion();
        }
        return singleConnection;
    }
    
    public void desconectar() {
        try{
            this.conectar.close();
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
