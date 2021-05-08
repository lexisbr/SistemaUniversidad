/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenlab;

/**
 *
 * @author lex
 */
public class NodoABB {
    private Object contenido;
    private NodoABB left;
    private NodoABB right;
    private int frecuencia;
    private int id;

    public NodoABB(Object contenido, NodoABB left, NodoABB right, int frecuencia, int id) {
        this.contenido = contenido;
        this.left = left;
        this.right = right;
        this.frecuencia = frecuencia;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Object getContenido() {
        return contenido;
    }

    public NodoABB getLeft() {
        return left;
    }

    public NodoABB getRight() {
        return right;
    }

    public int getFrecuencia() {
        return frecuencia;
    }

    public void setContenido(Object contenido) {
        this.contenido = contenido;
    }

    public void setLeft(NodoABB left) {
        this.left = left;
    }

    public void setRight(NodoABB right) {
        this.right = right;
    }

    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
