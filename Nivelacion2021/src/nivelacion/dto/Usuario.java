
package nivelacion.dto;


public class Usuario {
    private String rut;
    private String nombre;
    private int edad;
    private long telefono;

    public Usuario() {
    }

    public Usuario(String rut, String nombre, int edad, long telefono) {
        this.rut = rut;
        this.nombre = nombre;
        this.edad = edad;
        this.telefono = telefono;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Usuario{" + "rut=" + rut + ", nombre=" + nombre + ", edad=" + edad + ", telefono=" + telefono + '}';
    }
    
    
    
    
           
    
}
