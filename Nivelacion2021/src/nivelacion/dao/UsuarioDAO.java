
package nivelacion.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import nivelacion.dto.Usuario;
import nivelacion.util.Conexion;


public class UsuarioDAO extends Conexion {
//CRUD para la tabla usuarios    
    public Usuario obtenerUsuario(String rut){
        Usuario usuario = new Usuario();
        try{
            this.conectar();
            String sql = "SELECT * FROM usuario where rut='"+rut+"'";
            PreparedStatement st = this.conexion.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                usuario.setRut(rs.getString("rut"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEdad(rs.getInt("edad"));
                usuario.setTelefono(rs.getLong("telefono"));
            }            
        }
        catch(Exception e){
            System.out.println("Error : "+e.getMessage());
        }
        return usuario;
    }
    public ArrayList<Usuario> obtenerUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try{
            this.conectar();
            String sql = "SELECT * FROM usuario ORDER BY edad ASC";
            PreparedStatement st = this.conexion.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                //Crea un objeto Usuario
                Usuario usuario = new Usuario();
                usuario.setRut(rs.getString("rut"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setEdad(rs.getInt("edad"));
                usuario.setTelefono(rs.getLong("telefono"));
                //Agrega a la lista
                usuarios.add(usuario);
            }            
        }
        catch(Exception e){
            System.out.println("Error : "+e.getMessage());
        }
        return usuarios;
    }
    
    public boolean agregarUsuario(Usuario usuario){
        try{
            if(!existeUsuario(usuario)){
                this.conectar();
                String sql = "INSERT INTO usuario(rut,nombre,edad,telefono) VALUES(?,?,?,?)";
                PreparedStatement st = this.conexion.prepareStatement(sql);

                st.setString(1, usuario.getRut());
                st.setString(2, usuario.getNombre());
                st.setInt(3, usuario.getEdad());
                st.setLong(4, usuario.getTelefono());
                st.executeUpdate();
                return true;
            }
            else{
                System.out.println("El usuario ya existe");
                return false;
            }
        }
        catch(Exception e){
             System.out.println("Error : "+e.getMessage());
             return false;
        }
        
    }
    
    public boolean existeUsuario(Usuario usuario){
        boolean existe = false;
        try{
            this.conectar();
            String sql = "SELECT * FROM usuario WHERE rut='"+usuario.getRut()+"'";
            PreparedStatement st = this.conexion.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                existe = true;
            }
        }
        catch(Exception e){
            System.out.println("Error : "+e.getMessage());
        }
        return existe;
    }
    
    public boolean eliminarUsuario(Usuario usuario){
        try{
            this.conectar();
            if(existeUsuario(usuario)){
                String sql = "DELETE FROM usuario where rut=?";
                PreparedStatement st = this.conexion.prepareStatement(sql);
                st.setString(1, usuario.getRut());
                st.executeUpdate();
                return true;
                
            }
            else{
                System.out.println("Error el usuario no existe");
                return false;
            }
        }
        catch(Exception e){
            System.out.println("Error : "+e.getMessage());
            return false;
        }        
    }
    
    public boolean actualizarUsuario(Usuario usuario){
        try{
            this.conectar();
            if(existeUsuario(usuario)){
                String sql = "UPDATE usuario SET nombre=?, edad=?, telefono=? WHERE rut=?";
                PreparedStatement st = this.conexion.prepareStatement(sql);
                st.setString(1, usuario.getNombre());
                st.setInt(2, usuario.getEdad());
                st.setLong(3, usuario.getTelefono());
                st.setString(4, usuario.getRut());
                st.executeUpdate();
                return true;
            }
            else{
                System.out.println("Error el usuario no existe");
                return false;
            }
        }
        catch(Exception e){
            System.out.println("Error : "+e.getMessage());
            return false;
        }
    }
    public static void main(String[] args) {
        UsuarioDAO ud = new UsuarioDAO();
        ArrayList<Usuario> lista = new ArrayList<>();
        
        lista = ud.obtenerUsuarios();
        for (Usuario u: lista) {
            System.out.println(u);
        }
        
        Usuario newUsuario = new Usuario("25277126-3","Santiago Ilaja",5,888987777);
        
        ud.actualizarUsuario(newUsuario);
        System.out.println("");
        lista = ud.obtenerUsuarios();
        for (Usuario u: lista) {
            System.out.println(u);
        }
    }
}
