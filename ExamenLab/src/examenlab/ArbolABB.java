/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenlab;

import javax.swing.JOptionPane;

/**
 *
 * @author lex
 */
public class ArbolABB {
    private NodoABB root;
    
    public ArbolABB(){
        this.root = null;
    }
    
    public void insertarNodo(NodoABB nuevoNodo) {
        root = insertar(root, nuevoNodo);
    }

    private NodoABB insertar(NodoABB actualNodo, NodoABB nuevoNodo) {
        if (actualNodo == null)
        {
            return nuevoNodo;
        }

        if (nuevoNodo.getId() < actualNodo.getId())
        {
            actualNodo.setLeft(insertar(actualNodo.getLeft(), nuevoNodo));
        } else if (nuevoNodo.getId() > actualNodo.getId())
        {
            actualNodo.setRight(insertar(actualNodo.getRight(), nuevoNodo));
        } else
        {
            actualNodo.setFrecuencia(actualNodo.getFrecuencia()+1);
            return actualNodo;
        }
        return actualNodo;
    }
}
