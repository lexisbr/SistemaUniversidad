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
public class Usuario {
    public static final String COLABORADOR = "Colaborador";
    public static final String ESTUDIANTE = "Estudiante";
    public static final String SUPER = "Super";
    
    private int id;
    private String name;
    private String password;
    private String type;
    private Estudiante estudiante;

    public Usuario(int id, String name, String password, String type, Estudiante estudiante) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.type = type;
        this.estudiante = estudiante;
    }

    public Usuario(int id, String name, String password, String type) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    
    
}
