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
public class Horario {
    private int id;
    private int period;
    private String day;
    private int cursoId;
    private int salonId;
    private int edificioId;
    private int catedraticoId;

    public Horario(int id, int period, String day, int cursoId, int salonId, int edificioId, int catedraticoId) {
        this.id = id;
        this.period = period;
        this.day = day;
        this.cursoId = cursoId;
        this.salonId = salonId;
        this.edificioId = edificioId;
        this.catedraticoId = catedraticoId;
    }

    public int getId() {
        return id;
    }

    public int getPeriod() {
        return period;
    }

    public String getDay() {
        return day;
    }

    public int getCursoId() {
        return cursoId;
    }

    public int getSalonId() {
        return salonId;
    }

    public int getEdificioId() {
        return edificioId;
    }

    public int getCatedraticoId() {
        return catedraticoId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public void setSalonId(int salonId) {
        this.salonId = salonId;
    }

    public void setEdificioId(int edificioId) {
        this.edificioId = edificioId;
    }

    public void setCatedraticoId(int catedraticoId) {
        this.catedraticoId = catedraticoId;
    }

    
    
    
    
}
