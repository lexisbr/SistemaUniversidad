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
public class Curso {
    private int id;
    private String name;
    private int semester;
    private int credits;

    public Curso(int id, String name, int semester, int credits) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.credits = credits;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSemester() {
        return semester;
    }

    public int getCredits() {
        return credits;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setCredits(int creditos) {
        this.credits = credits;
    }
    
}
