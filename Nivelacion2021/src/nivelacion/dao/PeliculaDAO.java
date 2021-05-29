/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nivelacion.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import nivelacion.dto.Pelicula;
import nivelacion.dto.Usuario;
import nivelacion.util.Conexion;

/**
 *
 * @author EDUARDO
 */
public class PeliculaDAO extends Conexion{
    public ArrayList<Pelicula> obtenerPeliculas(){
        ArrayList<Pelicula> peliculas = new ArrayList<>();
        try{
            this.conectar();
            String sql = "SELECT * FROM pelicula ORDER BY year ASC";
            PreparedStatement st = this.conexion.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                //Crea un objeto Usuario
                Pelicula pelicula = new Pelicula();
                pelicula.setTitle(rs.getString("title"));
                pelicula.setYear(rs.getString("year"));
                pelicula.setGenre(rs.getString("genre"));
                
                //Agrega a la lista
                peliculas.add(pelicula);
            }            
        }
        catch(Exception e){
            System.out.println("Error : "+e.getMessage());
        }
        return peliculas;
    }
    
    public boolean agregarPelicula(Pelicula pelicula){
        try{
            if(!existePelicula(pelicula)){
                this.conectar();
                String sql = "INSERT INTO pelicula(title,year,genre) VALUES(?,?,?)";
                PreparedStatement st = this.conexion.prepareStatement(sql);

                st.setString(1, pelicula.getTitle());
                st.setString(2, pelicula.getYear());
                st.setString(3, pelicula.getGenre());
                                st.executeUpdate();
                return true;
            }
            else{
                System.out.println("La Película ya existe");
                return false;
            }
        }
        catch(Exception e){
             System.out.println("Error : "+e.getMessage());
             return false;
        }
        
    }
    
    public boolean existePelicula(Pelicula pelicula){
        boolean existe = false;
        try{
            this.conectar();
            String sql = "SELECT * FROM pelicula WHERE title='"+pelicula.getTitle()+"'";
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
    public boolean eliminarPelicula(Pelicula pelicula){
        try{
            this.conectar();
            if(existePelicula(pelicula)){
                String sql = "DELETE FROM pleicula where title=?";
                PreparedStatement st = this.conexion.prepareStatement(sql);
                st.setString(1, pelicula.getTitle());
                st.executeUpdate();
                return true;
                
            }
            else{
                System.out.println("Película el usuario no existe");
                return false;
            }
        }
        catch(Exception e){
            System.out.println("Error : "+e.getMessage());
            return false;
        }        
    }
    
    public boolean actualizarPelicula(Pelicula pelicula){
        try{
            this.conectar();
            if(existePelicula(pelicula)){
                String sql = "UPDATE pelicula SET year=?, genre=? WHERE title=?";
                PreparedStatement st = this.conexion.prepareStatement(sql);
                st.setString(1, pelicula.getYear());
                st.setString(2, pelicula.getGenre());
                st.setString(3, pelicula.getTitle());
                st.executeUpdate();
                return true;
            }
            else{
                System.out.println("Error la película no existe");
                return false;
            }
        }
        catch(Exception e){
            System.out.println("Error : "+e.getMessage());
            return false;
        }
    }
    public static void main(String[] args) {
        PeliculaDAO ud = new PeliculaDAO();
        ArrayList<Pelicula> lista = new ArrayList<>();
        
        lista = ud.obtenerPeliculas();
        for (Pelicula u: lista) {
            System.out.println(u);
        }
        
        Pelicula pelicula = new Pelicula("Shrek","1991","Drama");
        
        ud.actualizarPelicula(pelicula);
        System.out.println("");
        lista = ud.obtenerPeliculas();
        for (Pelicula u: lista) {
            System.out.println(u);
        }
    }
}
