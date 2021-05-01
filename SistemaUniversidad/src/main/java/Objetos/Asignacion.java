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

    private int studentId;
    private int horarioId;
    private int zona;
    private int final_test;

    public Asignacion(int studentId, int horarioId, int zona, int final_test) {
        this.studentId = studentId;
        this.horarioId = horarioId;
        this.zona = zona;
        this.final_test = final_test;
    }

    public int getStudentId() {
        return studentId;
    }

    public int getHorarioId() {
        return horarioId;
    }

    public int getZona() {
        return zona;
    }

    public int getFinal_test() {
        return final_test;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setHorarioId(int horarioId) {
        this.horarioId = horarioId;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

    public void setFinal_test(int final_test) {
        this.final_test = final_test;
    }

}
