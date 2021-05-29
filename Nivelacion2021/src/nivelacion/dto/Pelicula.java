/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nivelacion.dto;

/**
 *
 * @author EDUARDO
 */
public class Pelicula {
    private String title;
    private String year;
    private String genre;

    public Pelicula() {
    }

    public Pelicula(String title, String year, String genre) {
        this.title = title;
        this.year = year;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Pelicula{" + "title=" + title + ", year=" + year + ", genre=" + genre + '}';
    }
    
    
}
