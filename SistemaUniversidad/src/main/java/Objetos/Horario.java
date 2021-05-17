/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Estructuras.ListaCircular;

/**
 *
 * @author lex
 */
public class Horario {
    private int id;
    private String period;
    private String day;
    private Curso curso;
    private Salon salon;
    private Edificio edificio;
    private Catedratico catedratico;

    public Horario(int id) {
        this.id = id;
    }

    public Horario(int id, String period, String day, Curso curso, Salon salon, Edificio edificio, Catedratico catedratico) {
        this.id = id;
        this.period = period;
        this.day = day;
        this.curso = curso;
        this.salon = salon;
        this.edificio = edificio;
        this.catedratico = catedratico;
    }

    public int getId() {
        return id;
    }

    public String getPeriod() {
        return period;
    }

    public String getDay() {
        return day;
    }

    public Curso getCurso() {
        return curso;
    }

    public Salon getSalon() {
        return salon;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public Catedratico getCatedratico() {
        return catedratico;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public void setEdificio(Edificio edificio) {
        this.edificio = edificio;
    }

    public void setCatedratico(Catedratico catedratico) {
        this.catedratico = catedratico;
    }
    
}
