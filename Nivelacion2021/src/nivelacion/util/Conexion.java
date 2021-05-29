
package nivelacion.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private final String SERVER="localhost";
    private final String DATABASE1="unidad5";
    private final String USER1="root";
    private final String PASSWORD1="";
    
    protected Connection conexion;

    public Connection getConexion() {
        return conexion;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }
    
    public boolean conectar(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String cadena = "jdbc:mysql://"+SERVER+":3306/"+DATABASE1;
            this.conexion = DriverManager.getConnection(cadena, USER1, PASSWORD1);
            return true;           
        }
        catch(Exception e){
            System.out.println("Error "+e.getMessage());
            return false;
        }
    }
    
    public boolean desconectar(){
        try{
            this.conexion.close();
            return true;
        }
        catch(Exception e){
            System.out.println("Error "+e.getMessage());
            return false;
        }
    }
    
    public static void main(String[] args) {
        Conexion c = new Conexion();
        System.out.println(c.conectar());
    }
}
