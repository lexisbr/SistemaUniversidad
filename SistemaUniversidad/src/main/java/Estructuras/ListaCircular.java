/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estructuras;

import Objetos.Usuario;
import javax.swing.JOptionPane;

/**
 *
 * @author lex
 */
public class ListaCircular<T> {

    private Nodo<T> root;
    private Nodo<T> end;
    private int size;
    
    public static void main(String[] args){
        ListaCircular lista = new ListaCircular();
        lista.add(new Usuario(123, "Alejandrio", "Alejandrio", Usuario.ESTUDIANTE));
        lista.add(new Usuario(1234, "Gordo", "Alejandrio", Usuario.ESTUDIANTE));
        lista.add(new Usuario(1235, "Trolo", "Alejandrio", Usuario.ESTUDIANTE));
        
        lista.mostrarDatos();
    }

    public ListaCircular() {
        root = null;
        end = null;
    }

    public void add(T data) {
        Nodo nuevoNodo = new Nodo<T>(data);

        if (root == null)
        {
            root = nuevoNodo;
            end = nuevoNodo;
            root.setNext(root);
            root.setPrev(end);
        } else if (doesntExist(data))
        {
            end.setNext(nuevoNodo);
            nuevoNodo.setNext(root);
            nuevoNodo.setPrev(end);
            end = nuevoNodo;
            root.setPrev(end);
        } else
        {
            String id = getId(data);
            JOptionPane.showMessageDialog(null, "El dato: \"" + id + "\" ya existe.");
        }
    }

    private boolean doesntExist(T data) {
        String aux_id;
        String id = getId(data);

        Nodo<T> aux = root;

        do
        {
            aux_id = getId(aux.getData());
            if (aux_id.equals(id))
            {
                return false;
            }
            aux = aux.getNext();

        } while (aux != root);

        return true;
    }

    private String getId(T data) {
        if (data instanceof Usuario)
        {
            Usuario user = (Usuario) data;
            return String.valueOf(user.getId());
        }

        return null;
    }

    public void mostrarDatos() {
        Nodo<T> aux = root;

        do
        {
            if (aux.getData() instanceof Usuario)
            {
                Usuario user = (Usuario) aux.getData();
                System.out.println("************ USUARIO ************");
                System.out.println("ID: "+user.getId());
                System.out.println("Nombre: "+user.getName());
                System.out.println("Password: "+user.getPassword());
                System.out.println("Tipo: "+user.getType());
                if(aux.getNext() != null){
                    System.out.println("Siguiente: "+getId(aux.getNext().getData()));
                }
                if(aux.getPrev()!= null){
                    System.out.println("Anterior: "+getId(aux.getPrev().getData()));
                }
                
            }
            aux = aux.getNext();
        } while (aux != root);
    }

    private class Nodo<T> {

        private T data;
        private Nodo<T> next;
        private Nodo<T> prev;

        public Nodo(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public Nodo<T> getNext() {
            return next;
        }

        public Nodo<T> getPrev() {
            return prev;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setNext(Nodo<T> next) {
            this.next = next;
        }

        public void setPrev(Nodo<T> prev) {
            this.prev = prev;
        }

    }

}
