/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Estructuras.ListaSimple;

/**
 *
 * @author lex
 */
public class Edificio {
    private String name;
    private ListaSimple<Salon> listaSalones;

    public Edificio(String name) {
        this.name = name;
        this.listaSalones = new ListaSimple<Salon>();
    }

    public Edificio(String name, ListaSimple<Salon> listaSalones) {
        this.name = name;
        this.listaSalones = listaSalones;
    }

    public ListaSimple<Salon> getListaSalones() {
        return listaSalones;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setListaSalones(ListaSimple<Salon> listaSalones) {
        this.listaSalones = listaSalones;
    }
    
    
}
