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
public class Asignacion {

    private int id;
    private Estudiante estudiante;
    private int zona;
    private int final_test;
    private Horario horario;

    public Asignacion(int id, Estudiante estudiante, int zona, int final_test, Horario horario) {
        this.id = id;
        this.estudiante = estudiante;
        this.zona = zona;
        this.final_test = final_test;
        this.horario = horario;
    }

    public int getId() {
        return id;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public int getZona() {
        return zona;
    }

    public int getFinal_test() {
        return final_test;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

    public void setFinal_test(int final_test) {
        this.final_test = final_test;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }
    
    

}
