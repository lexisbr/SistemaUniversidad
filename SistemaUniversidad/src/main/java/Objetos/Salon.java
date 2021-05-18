/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

/**
 *
 * @author lex
 */
public class Salon {
    private int id;
    private int size;
    private String edificio;

    public Salon(int id, int size) {
        this.id = id;
        this.size = size;
    }

    public Salon(int id, int size, String edificio) {
        this.id = id;
        this.size = size;
        this.edificio = edificio;
    }

    public String getEdificio() {
        return edificio;
    }
    
    public int getId() {
        return id;
    }

    public int getSize() {
        return size;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }
    
    
}
